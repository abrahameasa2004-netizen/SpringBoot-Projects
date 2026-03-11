package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Admin;
import com.example.demo.service.IAdminService;

@Controller
public class LoginSignupController {

	@Autowired
	private IAdminService adminService;

	@GetMapping("/admin-login")
	public String showLoginForm() {
		return "admin-login";
	}

	@PostMapping("/admin-login")
	public String processLogin(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model) {
		boolean admin = adminService.authenticateAdmin(username, password);

		if (admin == true) {
			return "admin-home";
		} else {
			model.addAttribute("error", "Invalid username or password");
			return "admin-login";
		}
	}

	@GetMapping("/admin-signup")
	public String showSignupForm(Model model) {
		model.addAttribute("admin", new Admin());
		return "admin-signup";
	}

	@PostMapping("/admin-signup")
	public String processSignup(@ModelAttribute("admin") Admin admin, Model model) {
		Admin newAdmin = adminService.addAdmin(admin);

		if (newAdmin != null) {
			return "redirect:/admin-login";
		} else {
			model.addAttribute("error", "Failed to create user");
			return "admin-signup";
		}
	}
	
	@GetMapping("/logout")
    public String logout() {
        adminService.logoutAdmin();
        return "redirect:/admin-login";
    }
}