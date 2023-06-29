package com.babyshop.babyshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.babyshop.babyshop.models.User;
import com.babyshop.babyshop.service.UserService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AccountRestController {
	@Autowired
	UserService userService; 
 
	@PostMapping("/login/checkexits")
	public String verifierCode (@RequestBody String phone, HttpServletResponse response){
		User user = userService.findByPhone(phone.trim());
		if (user != null) {
			System.out.print("EXISTS: " + phone);
			return "exists";
		} else {
			System.out.print("NOT EXISTS: " + phone);
			return "not exists";
		}
	}
}
 