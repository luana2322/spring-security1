package com.security.demosecurity3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.demosecurity3.auth.AuthenticationRequest;
import com.security.demosecurity3.auth.RegisterRequest;
import com.security.demosecurity3.sevrice.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthenticationService authenticationService;
	
	@PostMapping("/register")
	public ResponseEntity<?> regiter(@RequestBody RegisterRequest registerRequest){
		return ResponseEntity.ok(authenticationService.register(registerRequest));
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest){
		return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
	}
	@GetMapping("/admin")
	public String hello() {
		return "hello";
	}
}
