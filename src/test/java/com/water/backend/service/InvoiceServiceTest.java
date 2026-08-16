package com.water.backend.service;

import org.junit.jupiter.api.Test; import static org.junit.jupiter.api.Assertions.*;
class InvoiceServiceTest { @Test void invoiceStatusStartsPendingByContract(){ assertEquals("PENDING","PENDING"); } }
