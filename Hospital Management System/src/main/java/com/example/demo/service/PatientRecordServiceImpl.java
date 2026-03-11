package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.PatientRecord;
import com.example.demo.repository.PatientRecordRepository;

@Service // This annotation makes the bean "findable" for @Autowired
public class PatientRecordServiceImpl implements IPatientRecordService {

    @Autowired
    private PatientRecordRepository recordRepository;

    @Override
    public List<PatientRecord> getRecordsByPatientId(Long patientId) {
        return recordRepository.findByPatientId(patientId);
    }

    @Override
    public void addRecord(PatientRecord record) {
        recordRepository.save(record);
    }
}