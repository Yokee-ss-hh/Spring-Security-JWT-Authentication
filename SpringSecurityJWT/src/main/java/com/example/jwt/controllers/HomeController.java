package com.example.jwt.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping(path = "/home")
	public String getHomeName() {
		return "YOKESH";
	}
}
