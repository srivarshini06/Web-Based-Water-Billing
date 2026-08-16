package com.water.backend.service.impl;

import com.water.backend.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.from:noreply@aquatrack.com}")
    private String fromEmail;

    @Value("${spring.mail.from-name:AquaTrack Water Billing}")
    private String fromName;

    @Override
    public void sendAlertNotification(String recipientEmail, String consumerName,
                                      String alertType, String message) {
        try {
            String subject = "Water Usage Alert - " + alertType;
            String body = buildAlertEmailBody(consumerName, alertType, message);
            sendHtmlEmail(recipientEmail, subject, body);
            log.info("Alert sent to: {}", recipientEmail);
        } catch (Exception e) {
            log.error("Failed to send alert to: {}", recipientEmail, e);
        }
    }

    @Override
    public void sendInvoiceNotification(String recipientEmail, String consumerName,
                                        Long invoiceId, BigDecimal amountDue) {
        try {
            String subject = "Your Water Bill is Ready - Invoice #" + invoiceId;
            String body = buildInvoiceEmailBody(consumerName, invoiceId, amountDue);
            sendHtmlEmail(recipientEmail, subject, body);
            log.info("Invoice notification sent to: {}", recipientEmail);
        } catch (Exception e) {
            log.error("Failed to send invoice to: {}", recipientEmail, e);
        }
    }

    @Override
    public void sendBillingCycleClosureNotification(String recipientEmail, String communityName) {
        try {
            String subject = "Billing Cycle Closed - " + communityName;
            String body = buildBillingCycleEmailBody(communityName);
            sendHtmlEmail(recipientEmail, subject, body);
            log.info("Billing closure notification sent to: {}", recipientEmail);
        } catch (Exception e) {
            log.error("Failed to send billing closure to: {}", recipientEmail, e);
        }
    }

    @Override
    public void sendLeakDetectionAlert(String recipientEmail, String consumerName,
                                       BigDecimal anomalousConsumption) {
        try {
            String subject = "Potential Water Leak Detected - Immediate Action Required";
            String body = buildLeakAlertEmailBody(consumerName, anomalousConsumption);
            sendHtmlEmail(recipientEmail, subject, body);
            log.info("Leak alert sent to: {}", recipientEmail);
        } catch (Exception e) {
            log.error("Failed to send leak alert to: {}", recipientEmail, e);
        }
    }

    @Override
    public void sendBulkEmail(List<String> recipients, String subject, String template) {
        for (String recipient : recipients) {
            try {
                sendHtmlEmail(recipient, subject, template);
            } catch (Exception e) {
                log.error("Failed to send bulk email to: {}", recipient, e);
            }
        }
        log.info("Bulk email sent to {} recipients", recipients.size());
    }

    // Helper methods

    private void sendHtmlEmail(String to, String subject, String htmlBody) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(fromEmail, fromName);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);

        mailSender.send(message);
    }

    private String buildAlertEmailBody(String consumerName, String alertType, String message) {
        return """
            <html>
            <body style="font-family: Arial, sans-serif;">
                <h2>Water Usage Alert</h2>
                <p>Dear %s,</p>
                <p style="color: #d32f2f; font-weight: bold;">Alert Type: %s</p>
                <p>%s</p>
                <p style="margin-top: 20px; color: #666;">
                    Please log in to your AquaTrack dashboard to view your detailed consumption report 
                    and take necessary action.
                </p>
                <p style="margin-top: 20px; color: #999; font-size: 12px;">
                    This is an automated message. Please do not reply to this email.
                </p>
            </body>
            </html>
            """.formatted(consumerName, alertType, message);
    }

    private String buildInvoiceEmailBody(String consumerName, Long invoiceId, BigDecimal amountDue) {
        return """
            <html>
            <body style="font-family: Arial, sans-serif;">
                <h2>Your Water Bill</h2>
                <p>Dear %s,</p>
                <p>Your water bill for Invoice #%d is now ready.</p>
                <p style="font-size: 18px; font-weight: bold; color: #1976d2;">
                    Amount Due: Rs %.2f
                </p>
                <p>Please pay within 30 days to avoid late charges.</p>
                <p style="margin-top: 20px;">
                    <a href="http://aquatrack.local/invoices/%d" style="background-color: #1976d2; color: white; padding: 10px 20px; text-decoration: none; border-radius: 4px;">
                        View Invoice
                    </a>
                </p>
                <p style="margin-top: 20px; color: #999; font-size: 12px;">
                    Thank you for choosing AquaTrack!
                </p>
            </body>
            </html>
            """.formatted(consumerName, invoiceId, amountDue, invoiceId);
    }

    private String buildBillingCycleEmailBody(String communityName) {
        return """
            <html>
            <body style="font-family: Arial, sans-serif;">
                <h2>Billing Cycle Closed</h2>
                <p>Dear Resident,</p>
                <p>The billing cycle for %s has been finalized.</p>
                <p>Your invoices are now available in your AquaTrack dashboard.</p>
                <p style="margin-top: 20px;">
                    <a href="http://aquatrack.local/dashboard" style="background-color: #1976d2; color: white; padding: 10px 20px; text-decoration: none; border-radius: 4px;">
                        Access Your Dashboard
                    </a>
                </p>
            </body>
            </html>
            """.formatted(communityName);
    }

    private String buildLeakAlertEmailBody(String consumerName, BigDecimal anomalousConsumption) {
        return """
            <html>
            <body style="font-family: Arial, sans-serif; background-color: #ffebee;">
                <h2 style="color: #d32f2f;">🚨 Potential Water Leak Detected</h2>
                <p>Dear %s,</p>
                <p style="color: #d32f2f; font-weight: bold;">
                    Our system has detected abnormally high water consumption in your household.
                </p>
                <p>
                    <strong>Detected Consumption:</strong> %.2f kL (unusually high compared to your average)
                </p>
                <p style="margin-top: 15px;">
                    This may indicate:
                </p>
                <ul>
                    <li>A leaking pipe or faucet</li>
                    <li>An overflowing toilet tank</li>
                    <li>An unmeter fixture</li>
                </ul>
                <p style="margin-top: 20px; color: #d32f2f;">
                    <strong>Recommended Action:</strong> Check your property immediately and contact maintenance if needed.
                </p>
                <p style="margin-top: 20px; color: #999; font-size: 12px;">
                    This is an automated alert. Please do not reply to this email.
                </p>
            </body>
            </html>
            """.formatted(consumerName, anomalousConsumption);
    }
}