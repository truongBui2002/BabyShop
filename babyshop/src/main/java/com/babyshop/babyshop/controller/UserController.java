package com.babyshop.babyshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.babyshop.babyshop.repositories.BrandRepository;
//import com.babyshop.babyshop.repositories.BrandRepository;
import com.babyshop.babyshop.repositories.ProductRepository;
@Controller
public class UserController {
	
	@Autowired
	BrandRepository brandRepository;
	
	@Autowired
	ProductRepository productRepository;

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
	@GetMapping("/email")
	public String email() {
		return "email";
	}
	@GetMapping("/phone")
	public String phone() {
		return "phone";
	}
}
