package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
      private final UserService service;
      public AuthController (UserService service) {
    	  this.service=service;
      }
      @PostMapping("/register")
      public String register(@RequestBody RegisterRequest request) {
    	  return service.register(request);
      }
      @PostMapping("/login")
      public AuthResponse login(@RequestBody LoginRequest request){

          return service.login(request);

      }
}
