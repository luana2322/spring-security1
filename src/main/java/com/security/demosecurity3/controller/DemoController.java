package com.security.demosecurity3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

	@GetMapping("/admin")
	public ResponseEntity<?> admin(){
		return ResponseEntity.ok("This is Admin Route");
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> user(){
		return ResponseEntity.ok("This is Use Route");
	}
}
