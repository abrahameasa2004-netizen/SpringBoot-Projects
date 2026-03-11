package com.example.demo.service;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;

@Service
public class PatientServiceImpl implements IPatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public Patient addPatient(Patient patient) {
		return patientRepository.save(patient);
	}

	@Override
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	@Override
	public void deletePatient(Long id) {
		patientRepository.deleteById(id);
	}

	@Override
	public void updatePatient(Patient patient) {
		patientRepository.save(patient);
	}

	@Override
	public Patient getPatientById(Long id) {
		Optional<Patient> optional = patientRepository.findById(id);
		Patient patient = null;
		if (optional.isPresent()) {
			patient = optional.get();
		} else {
			throw new RuntimeException();
		}
		return patient;
	}

}