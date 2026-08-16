package com.water.backend.service;

import java.util.List;

public interface EmailService {
    void sendAlertNotification(String recipientEmail, String consumerName,
                               String alertType, String message);

    void sendInvoiceNotification(String recipientEmail, String consumerName,
                                 Long invoiceId, java.math.BigDecimal amountDue);

    void sendBillingCycleClosureNotification(String recipientEmail, String communityName);

    void sendLeakDetectionAlert(String recipientEmail, String consumerName,
                                java.math.BigDecimal anomalousConsumption);

    void sendBulkEmail(List<String> recipients, String subject, String template);
}