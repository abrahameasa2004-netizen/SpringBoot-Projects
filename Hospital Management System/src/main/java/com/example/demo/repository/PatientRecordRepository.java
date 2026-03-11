
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.PatientRecord;
import java.util.List;

@Repository
public interface PatientRecordRepository extends JpaRepository<PatientRecord, Long> {
    // Custom query to find records by the Patient's ID
    List<PatientRecord> findByPatientId(Long patientId);
}