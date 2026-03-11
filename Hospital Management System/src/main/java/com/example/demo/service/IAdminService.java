package com.example.demo.service;


import com.example.demo.model.Admin;
import com.example.demo.model.Appointment;
import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;

public interface IAdminService {

	Admin addAdmin(Admin admin);

	Doctor addDoctor(Doctor doctor);

	Patient addPatient(Patient patient);

	Appointment addAppointment(Appointment appointment);

	boolean authenticateAdmin(String username, String password);

	void logoutAdmin();

}