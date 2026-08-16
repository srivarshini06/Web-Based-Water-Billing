package com.water.backend.service;

import com.water.backend.dto.request.InvoiceRequest;
import com.water.backend.dto.response.InvoiceResponse;
import java.util.List;

public interface InvoiceService {
    InvoiceResponse generateInvoiceForConsumer(Long billingCycleId, Long consumerId);

    void generateInvoicesForBillingCycle(Long billingCycleId);

    InvoiceResponse getInvoiceById(Long id);

    List<InvoiceResponse> getInvoicesByConsumer(Long consumerId);

    List<InvoiceResponse> getInvoicesByBillingCycle(Long billingCycleId);

    void markInvoiceAsPaid(Long invoiceId);

    void deleteInvoice(Long id);

    List<InvoiceResponse> getAllInvoices();
}