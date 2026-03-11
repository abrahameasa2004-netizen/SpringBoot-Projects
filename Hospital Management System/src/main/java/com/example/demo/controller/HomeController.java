package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}

	@GetMapping("/doctor-home")
	public String doctor() {
		return "doctor";
	}

	@GetMapping("/patient-home")
	public String patient() {
		return "patient";
	}
}