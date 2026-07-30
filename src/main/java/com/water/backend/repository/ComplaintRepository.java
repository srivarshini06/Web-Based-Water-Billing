package com.water.backend.repository;

import com.water.backend.entity.Complaint;
import com.water.backend.entity.Resident;
import com.water.backend.enums.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByResident(Resident resident);

    List<Complaint> findByStatus(ComplaintStatus status);
}