package com.babyshop.babyshop.restcontroller;

import java.time.Duration;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

	@PostMapping("/login/checkexits")
	public String verifierCode(@RequestBody String phone) {
		if (userService.userIsExists(phone.trim().replace("\"", ""))) {
			return "exists";
		} else {
			return "not exists";
		}
	}
	
	@PostMapping("/login/phone/token")
	public String saveToke(@RequestBody String token) {
		session.setAttribute("tokenPhone", token);
		System.out.println("tokenPhone: " + token) ;
		return "";
	}
	
	@PostMapping("/login/email/exists")
	public String code(@RequestBody String email) {

		String emailEx = email.trim().replace("\"", "");
		if (userService.userIsExists(emailEx)) {
			return "exists";
		} else {
			return "not exists";
		}
	}
	
	@PostMapping("/login/sendcode/email")
	public String sendCodeEmail(@RequestBody String email) {
		String emailEx = email.trim().replace("\"", "");
		RandomKey randomKey = new RandomKey();
		String code = randomKey.getNumber(6);
		sendMailService.sendCode(emailEx, code);
		session.setAttribute("code", code);
		session.setAttribute("emailVerifier", emailEx);
		LocalDateTime start = LocalDateTime.now();
		session.setAttribute("start", start);
		return "success";
	}

	@PostMapping("/login/authen/email")
	public String autenEmail(@RequestBody String data) {
		ObjectMapper obj = new ObjectMapper();
		String[] array; // index 1 : email, index2: code;
		try {
			array = obj.readValue(data, String[].class);
			String email = (String) session.getAttribute("emailVerifier");
			String code = (String) session.getAttribute("code");
			LocalDateTime start = (LocalDateTime) session.getAttribute("start");
			if (email != null && code != null && start != null) {
				LocalDateTime end = LocalDateTime.now();
				Duration delay = Duration.between(start, end);
				// có hiệu lực 5 phút
				if (delay.getSeconds() < 5 * 60) {
					if (email.equals(array[0]) && code.equals(array[1])) {
						session.setAttribute("emailConfirm", email);
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
