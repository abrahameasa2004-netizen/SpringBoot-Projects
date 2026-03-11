package com.example.demo.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

	@Autowired
	AppointmentRepository appointmentRepository;

	@Override
	public Appointment addAppointment(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

	@Override
	public List<Appointment> getAllAppointments() {
		return appointmentRepository.findAll();
	}

	@Override
	public Appointment updateAppointment(Long id, Appointment appointment) {
		Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
		if (!appointmentOptional.isPresent()) {
			throw new RuntimeException("Appointment not found with id " + id);
		}

		appointment.setId(id);
		return appointmentRepository.save(appointment);
	}

	@Override
	public void deleteAppointmentById(Long id) {
		appointmentRepository.deleteById(id);
	}

	@Override
	public Appointment getAppointmentById(long id) {
		Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
		if (!appointmentOptional.isPresent()) {
			throw new RuntimeException("Appointment not found with id " + id);
		}
		return appointmentOptional.get();
	}
}