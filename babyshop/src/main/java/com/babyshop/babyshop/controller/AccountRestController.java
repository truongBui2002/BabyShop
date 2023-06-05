package com.babyshop.babyshop.controller;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.babyshop.babyshop.service.SendMailService;
import com.babyshop.babyshop.service.UserService;
import com.babyshop.babyshop.util.RandomKey;
  
import jakarta.servlet.http.HttpServletResponse;
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
		if (userService.userIsExists(email.trim().replace("\"", ""))) {
			return "exists";
		} else { 
			RandomKey randomKey = new RandomKey();
			String code = randomKey.getNumber(6);
			session.setAttribute("code", code); 
			session.setAttribute("emailVerifier", email); 
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override 
				public void run() { 
					session.removeAttribute("code");
					session.removeAttribute("emailVerifier");
				}
			}, 10 * 60 * 1000); // code có hiệu lực 10 phút
			sendMailService.sendCode(email, code);
			return "not exists";
		}   
	}   
 
	@PostMapping("/login/checkexits")
	public String verifierCode (@RequestBody String phone, HttpServletResponse response){
		if (userService.userIsExists(phone.trim().replace("\"", ""))) {
			return "exists";
		} else {
			return "not exists";
		}
	}
}
