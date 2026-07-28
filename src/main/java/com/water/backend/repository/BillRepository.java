package com.water.backend.repository;

import com.water.backend.entity.Bill;
import com.water.backend.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {

    /**
     * Returns all bills belonging to a resident.
     */
    List<Bill> findByResident(Resident resident);

    /**
     * Checks whether a bill has already been generated
     * for the resident in the given billing month.
     */
    boolean existsByResidentAndBillMonth(Resident resident, LocalDate billMonth);

}