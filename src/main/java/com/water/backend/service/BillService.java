package com.water.backend.service;

import com.water.backend.dto.request.BillRequest;
import com.water.backend.dto.response.BillResponse;

import java.util.List;

public interface BillService {

    /**
     * Generates a new water bill based on water reading details.
     *
     * @param request bill generation request containing reading ID and bill month
     * @return generated bill details
     */
    BillResponse generateBill(BillRequest request);


    /**
     * Retrieves all generated bills.
     *
     * @return list of all bills
     */
    List<BillResponse> getAllBills();


    /**
     * Retrieves a bill using its unique ID.
     *
     * @param id bill ID
     * @return bill details
     */
    BillResponse getBillById(Long id);


    /**
     * Retrieves all bills belonging to a resident.
     *
     * @param residentId resident ID
     * @return list of resident bills
     */
    List<BillResponse> getResidentBills(Long residentId);


    /**
     * Marks a bill as paid.
     *
     * @param id bill ID
     * @return updated bill details
     */
    BillResponse payBill(Long id);
}