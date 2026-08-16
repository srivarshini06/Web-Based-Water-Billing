package com.water.backend.service.impl;

import com.water.backend.dto.response.WaterUsageAlertResponse;
import com.water.backend.entity.Resident;
import com.water.backend.entity.WaterReading;
import com.water.backend.entity.WaterUsageAlert;
import com.water.backend.mapper.WaterUsageAlertMapper;
import com.water.backend.repository.ResidentRepository;
import com.water.backend.repository.WaterReadingRepository;
import com.water.backend.repository.WaterUsageAlertRepository;
import com.water.backend.service.AlertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlertServiceImpl implements AlertService {
    private final ResidentRepository residentRepository;
    private final WaterReadingRepository readingRepository;
    private final WaterUsageAlertRepository alertRepository;

    @Value("${water.alert.threshold:100.0}") private Double thresholdLitres;

    @Override @Scheduled(fixedRateString = "${water.alert.threshold-check-ms:3600000}") @Transactional
    public void detectThresholdViolations() {
        for (Resident resident : residentRepository.findAll()) {
            List<WaterReading> readings = readingRepository.findByResident(resident);
            if (readings.isEmpty()) continue;
            WaterReading latest = readings.stream().max((a,b) -> a.getReadingDate().compareTo(b.getReadingDate())).orElse(null);
            if (latest != null && latest.getConsumption() != null && latest.getConsumption() > thresholdLitres) {
                createIfAbsentToday(resident, "THRESHOLD", "Water consumption of " + latest.getConsumption() + " litres exceeds threshold of " + thresholdLitres + " litres", latest.getConsumption(), thresholdLitres);
            }
        }
    }

    @Override @Scheduled(fixedRateString = "${water.alert.outlier-check-ms:7200000}") @Transactional
    public void detectOutliers() {
        for (Resident resident : residentRepository.findAll()) {
            List<WaterReading> readings = readingRepository.findByResident(resident);
            if (readings.size() < 3) continue;
            DoubleSummaryStatistics stats = readings.stream().filter(r -> r.getConsumption() != null).mapToDouble(WaterReading::getConsumption).summaryStatistics();
            if (stats.getCount() < 3) continue;
            double mean = stats.getAverage();
            double variance = readings.stream().filter(r -> r.getConsumption() != null).mapToDouble(r -> Math.pow(r.getConsumption() - mean, 2)).average().orElse(0);
            double limit = mean + 2 * Math.sqrt(variance);
            WaterReading latest = readings.stream().max((a,b) -> a.getReadingDate().compareTo(b.getReadingDate())).orElse(null);
            if (latest != null && latest.getConsumption() != null && latest.getConsumption() > limit) {
                createIfAbsentToday(resident, "OUTLIER", "Unusual water consumption of " + latest.getConsumption() + " litres detected (average: " + Math.round(mean) + " litres, threshold: " + Math.round(limit) + " litres)", latest.getConsumption(), limit);
            }
        }
    }

    private void createIfAbsentToday(Resident resident, String type, String message, double detected, double threshold) {
        LocalDate today = LocalDate.now();
        boolean exists = alertRepository.findByResident(resident).stream().anyMatch(a -> a.getCreatedAt() != null && a.getCreatedAt().toLocalDate().equals(today) && type.equals(a.getAlertType()));
        if (!exists) alertRepository.save(WaterUsageAlert.builder().resident(resident).alertType(type).message(message).detectedValue(detected).threshold(threshold).acknowledged(false).build());
    }

    @Override @Transactional(readOnly = true)
    public List<WaterUsageAlertResponse> getAlertsByResident(Long residentId) {
        Resident resident = residentRepository.findById(residentId).orElseThrow(() -> new RuntimeException("Resident not found"));
        return alertRepository.findByResident(resident).stream().map(WaterUsageAlertMapper::toResponse).toList();
    }

    @Override @Transactional
    public WaterUsageAlertResponse acknowledgeAlert(Long alertId) {
        WaterUsageAlert alert = alertRepository.findById(alertId).orElseThrow(() -> new RuntimeException("Alert not found"));
        alert.setAcknowledged(true); return WaterUsageAlertMapper.toResponse(alertRepository.save(alert));
    }
    @Autowired
    private EmailService emailService;

    private void createAndNotifyAlert(Consumer consumer, String alertType,
                                      String message, WaterUsageAlert.Severity severity) {
        WaterUsageAlert alert = new WaterUsageAlert();
        alert.setConsumer(consumer);
        alert.setAlertType(alertType);
        alert.setMessage(message);
        alert.setSeverity(severity);
        alert.setCreatedAt(new Date());
        alert.setResolved(false);

        alertRepository.save(alert);

        // Send email notification
        emailService.sendAlertNotification(
                consumer.getEmail(),
                consumer.getFirstName() + " " + consumer.getLastName(),
                alertType,
                message
        );

        log.info("Alert created and notified for consumer: {}", consumer.getId());
    }
}
