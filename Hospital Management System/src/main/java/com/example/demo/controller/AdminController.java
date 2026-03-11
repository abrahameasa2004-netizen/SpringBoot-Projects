package com.example.demo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Appointment;
import com.example.demo.model.Bill;
import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.model.PatientRecord;
import com.example.demo.repository.BillRepository;
import com.example.demo.service.IAppointmentService;
import com.example.demo.service.IDoctorService;
import com.example.demo.service.IPatientRecordService;
import com.example.demo.service.IPatientService;

@Controller
public class AdminController {

	
	@Autowired
	private IPatientRecordService recordService; // This fixes the red underline error
	
	@Autowired
	private IDoctorService doctorService;

	@Autowired
	private IPatientService patientService;

	@Autowired
	private IAppointmentService appointmentService;
	
	@Autowired
	private BillRepository billRepository; // Directly injecting for simplicity, or use a Service

	
	
	// View All Doctors

	@GetMapping("/doctors")
	public String viewAllDoctors(Model model) {
		List<Doctor> doctors = doctorService.getAllDoctors();
		model.addAttribute("doctorsList", doctors);
		return "doctor";
	}

	@GetMapping("/addNewDoctorForm")
	public String createDoctorForm(Model model) {
		model.addAttribute("doctor", new Doctor());
		return "doctor-form";
	}

	@PostMapping("/saveNewDoctor")
	public String saveDoctor(@ModelAttribute("doctor") Doctor doctor) {
		doctorService.addDoctor(doctor);
		return "redirect:/doctors";
	}

	// Update Doctor

	@GetMapping("/updateDoctorForm/{id}")
	public String showDoctorUpdateForm(@PathVariable(value = "id") Long id, Model model) {
		Doctor doctor = doctorService.getDoctorById(id);
		model.addAttribute("doctor", doctor);
		return "doctor-form";             //Here changed
	}

	@PostMapping("/updateDoctor")
	public String updateDoctor(Doctor doctor) {
		doctorService.updateDoctor(doctor);
		return "redirect:/doctors";
	}

	// Delete Doctor
	@GetMapping("/delete/doctor/{id}")
	public String deleteDoctor(@PathVariable("id") Long id) {
		doctorService.deleteDoctor(id);
		return "redirect:/doctors";
	}

	// View All Patients

	@GetMapping("/patients")
	public String viewAllPatients(Model model) {
		List<Patient> patients = patientService.getAllPatients();
		model.addAttribute("patientsList", patients);
		return "patient";
	}

	@GetMapping("/addNewPatientForm")
	public String createPatientForm(Model model) {
		model.addAttribute("patient", new Patient());
		return "patient-form";
	}

	@PostMapping("/saveNewPatient")
	public String savePatient(@ModelAttribute("patient") Patient patient) {
		patientService.addPatient(patient);
		return "redirect:/patients";
	}

	// Update Patient

	@GetMapping("/updatePatientForm/{id}")
	public String showPatientUpdateForm(@PathVariable(value = "id") Long id, Model model) {
		Patient patient = patientService.getPatientById(id);
		model.addAttribute("patient", patient);
		return "patient-update";
	}

	@PostMapping("/updatePatient")
	public String updatePatient(Patient patient) {
		patientService.updatePatient(patient);
		return "redirect:/patients";
	}

	// Delete Patient
	@GetMapping("/delete/patient/{id}")
	public String deletePatient(@PathVariable("id") Long id) {
		patientService.deletePatient(id);
		return "redirect:/patients";
	}

	// View All Appointments
	@GetMapping("/admin-home")
	public String back() {
		return "admin-home";
	}

	@GetMapping("/appointments")
	public String listAllAppointments(Model model) {
		List<Appointment> appointment = appointmentService.getAllAppointments();
		model.addAttribute("appointments", appointment);
		return "appointment";
	}
	
	@GetMapping("/doctor")
	public String listDoctorAppointments(Model model) {
		List<Appointment> appointment = appointmentService.getAllAppointments();
		model.addAttribute("appointmentsList", appointment);
		return "doctor-home";
	}
	
	@GetMapping("/patient")
	public String listPatientAppointments(Model model) {
		List<Appointment> appointment = appointmentService.getAllAppointments();
		model.addAttribute("appointmentsList", appointment);
		return "patient-home";
	}
	
	
	// Add new Appointment

	@GetMapping("/addNewAppointmentForm")
	public String createAppointmentForm(Model model) {
		List<Patient> patients = patientService.getAllPatients();
		List<Doctor> doctors = doctorService.getAllDoctors();
		model.addAttribute("patientsList", patients);
		model.addAttribute("doctorsList", doctors);
		model.addAttribute("appointment", new Appointment());
		return "appointment-form";
	}

	@PostMapping("/saveNewAppointment")
	public String saveAppointment(@ModelAttribute("appointment") Appointment appointment) {
		appointmentService.addAppointment(appointment);
		return "redirect:/appointments";
	}

	@GetMapping("/confirm/appointment/{id}")
	public String confirmAppointment(@PathVariable("id") Long id) {
	    Appointment appointment = appointmentService.getAppointmentById(id);
	    appointment.setStatus("Confirmed");
	    appointmentService.updateAppointment(id, appointment); // Saves the change
	    return "redirect:/appointments"; // Redirect back to pending list
	}
	@GetMapping("/confirmed-appointments") // This must match the th:href in your other pages
	public String viewConfirmedAppointments(Model model) {
	    List<Appointment> all = appointmentService.getAllAppointments();
	    List<Appointment> confirmed = all.stream()
	        .filter(a -> "Confirmed".equals(a.getStatus()))
	        .toList();
	    model.addAttribute("confirmedList", confirmed); // This matches the th:each="appointment : ${confirmedList}"
	    return "confirmed-appointments"; // This must match your file name exactly
	}
	// Delete Appointment

	@GetMapping("/delete/appointment/{id}")
	public String deleteAppointment(@PathVariable("id") Long id) {
		appointmentService.deleteAppointmentById(id);
		return "redirect:/appointments";
	}
	
//	
//	@GetMapping("/generate-bill/{id}")
//	public String showBillForm(@PathVariable("id") Long id, Model model) {
//	    Appointment app = appointmentService.getAppointmentById(id);
//	    Bill bill = new Bill();
//	    bill.setAppointment(app);
//	    model.addAttribute("bill", bill);
//	    return "bill-form";
//	}
//	
	@GetMapping("/billing")
	public String viewAllBills(Model model) {
	    List<Bill> bills = billRepository.findAll();
	    model.addAttribute("billsList", bills);
	    return "billing-list"; // The HTML page to view all bills
	}

	@GetMapping("/create-bill/{id}")
	public String showBillForm(@PathVariable("id") Long appointmentId, Model model) {
	    Appointment app = appointmentService.getAppointmentById(appointmentId);
	    Bill bill = new Bill();
	    bill.setAppointment(app);
	    model.addAttribute("bill", bill);
	    return "bill-form"; // The HTML form to enter the amount
	}

	@PostMapping("/saveBill")
	public String saveBill(@ModelAttribute("bill") Bill bill) {
	    bill.setBillingStatus("Pending");
	    billRepository.save(bill);
	    return "redirect:/billing";
	}
	
	@GetMapping("/patient-history/{patientId}")
	public String viewHistory(@PathVariable("patientId") Long patientId, Model model) {
	    List<PatientRecord> records = recordService.getRecordsByPatientId(patientId);
	    model.addAttribute("records", records);
	    model.addAttribute("patient", patientService.getPatientById(patientId));
	    return "patient-history";
	}
	
	
	
	
}