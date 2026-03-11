package com.example.demo.service;
import java.util.List;
import com.example.demo.model.Appointment;

public interface IAppointmentService {

	Appointment addAppointment(Appointment appointment);
	
	List<Appointment> getAllAppointments();

	void deleteAppointmentById(Long id);

	Appointment updateAppointment(Long id, Appointment appointment);

	Appointment getAppointmentById(long id);
}