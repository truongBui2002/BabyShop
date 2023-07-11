package com.babyshop.babyshop.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.babyshop.babyshop.service.SendMailService;
import com.babyshop.babyshop.service.UserService;
import com.babyshop.babyshop.util.RandomKey;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;

@RestController
public class AccountRestController {
	@Autowired
	UserService userService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	SendMailService sendMailService; 
	  
	@PostMapping("/login/sendcode/email") 
	public String code(@RequestBody String email){
		
		String emailEx = email.trim().replace("\"", "");
		if (userService.userIsExists(emailEx)) {
			return "exists"; 
		} else { 
			RandomKey randomKey = new RandomKey();
			String code = randomKey.getNumber(6);
			System.out.println("code: " + code);
			sendMailService.sendCode(emailEx, code);
			session.setAttribute("code", code);  
			session.setAttribute("emailVerifier", emailEx); 
			LocalDateTime start = LocalDateTime.now();
			session.setAttribute("start", start);
			return "not exists";
		}   
	}   
 
	@PostMapping("/login/checkexits")
	public String verifierCode (@RequestBody String phone){
		if (userService.userIsExists(phone.trim().replace("\"", ""))) {
			return "exists";
		} else {
			return "not exists";
		}
	}
	
	@PostMapping("/login/authen/email")
	public String autenEmail(@RequestBody String data) {
		ObjectMapper obj = new ObjectMapper();
		String[] array; // index 1 : email, index2: code;
		try {
			array = obj.readValue(data, String[].class);
			String email = (String)session.getAttribute("emailVerifier");
			String code = (String)session.getAttribute("code");
			LocalDateTime start = (LocalDateTime)session.getAttribute("start");
			if(email!=null&&code!=null&&start!=null) {
				LocalDateTime end = LocalDateTime.now();
				Duration delay = Duration.between(start, end);
				// có hiệu lực 5 phút
				if(delay.getSeconds()<5*60) {
					if(email.equals(array[0])&& code.equals(array[1])) {
						return "true";
					}
				}	
			}
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		return "false";
	}
	
}
