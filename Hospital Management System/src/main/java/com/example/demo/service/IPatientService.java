package com.example.demo.service;


import java.util.List;

import com.example.demo.model.Patient;

public interface IPatientService {

	Patient addPatient(Patient patient);

	List<Patient> getAllPatients();

	void deletePatient(Long id);

	void updatePatient(Patient patient);

	Patient getPatientById(Long id);
}