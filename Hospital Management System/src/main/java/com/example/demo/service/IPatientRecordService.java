package com.example.demo.service;
import com.example.demo.model.PatientRecord;
import java.util.List;

public interface IPatientRecordService {
    List<PatientRecord> getRecordsByPatientId(Long patientId);
    void addRecord(PatientRecord record);
}