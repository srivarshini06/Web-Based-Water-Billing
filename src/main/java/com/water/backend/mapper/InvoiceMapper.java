package com.water.backend.mapper;

import com.water.backend.dto.response.InvoiceResponse;
import com.water.backend.entity.Invoice;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {

    public InvoiceResponse toResponse(Invoice invoice) {
        if (invoice == null) {
            return null;
        }

        return InvoiceResponse.builder()
                .id(invoice.getId())
                .billingCycleId(invoice.getBillingCycle() != null ? invoice.getBillingCycle().getId() : null)
                .consumerId(invoice.getConsumer() != null ? invoice.getConsumer().getId() : null)
                .consumerName(invoice.getConsumer() != null ?
                        invoice.getConsumer().getFirstName() + " " + invoice.getConsumer().getLastName() : "")
                .amountDue(invoice.getAmountDue())
                .issueDate(invoice.getIssueDate())
                .dueDate(invoice.getDueDate())
                .paidDate(invoice.getPaidDate())
                .status(invoice.getStatus())
                .remarks(invoice.getRemarks())
                .build();
    }
}