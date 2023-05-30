package com.babyshop.babyshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		
		return "login";
	}
	@GetMapping("/forgotpass")
	public String forgotPass() {
		return "forgotpass";
	}
	@GetMapping("/viewprofile")
	public String viewProfile() {
		return "viewprofile";
	}
	
}
