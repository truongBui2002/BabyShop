package com.babyshop.babyshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.babyshop.babyshop.models.User;
import com.babyshop.babyshop.service.SendMailService;
import com.babyshop.babyshop.service.UserService;

//import com.babyshop.babyshop.repositories.BrandRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private LoadDataController loadDataController;

	@Autowired
	HttpSession session;

	@Autowired
	SendMailService sendMailService;

	@Autowired
	UserService userService;

	@GetMapping("/login")
	public String login(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "login";
	}

	@GetMapping("/forgotpassword")
	public String forgotPass(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "forgotpass";
	}

	@GetMapping("/viewprofile")
	public String viewProfile(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal() instanceof UserDetails) {
			User user = (User) authentication.getPrincipal();
			session.setAttribute("user", user);
		}

		return "viewprofile";
	}

	@GetMapping("/email")
	public String email(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "email";
	}

	@GetMapping("/phone")
	public String phone(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "phone";
	}

	@GetMapping("/customer")
	public String customer() {
		return "test";
	}

	@PostMapping("/register")
	public String register(@RequestParam(name = "phoneNumber") String phoneOrMail,
			@RequestParam(name = "otpValue", defaultValue = "null") String otpValue,
			@RequestParam(name = "password") String password, ModelMap modelMap) {
		User user = new User();
		
		if(phoneOrMail.matches("\\d+")) {
			user.setPhoneNumber(phoneOrMail);
			user.setPassword(password);
			userService.saveUser(user);
		} else{
			String email = (String)session.getAttribute("emailVerifier");
			String code = (String)session.getAttribute("code");
			System.out.println(email.replace("\"", ""));
			System.out.println(code);
			if(phoneOrMail.equals(email.trim().replace("\"", ""))&&otpValue.equals(code)) {
				user.setEmail(phoneOrMail);
				user.setPassword(password);
				userService.saveUser(user);
			}else {
				session.setAttribute("phoneOrMail", phoneOrMail);
				modelMap.addAttribute("openTab2", true);
				modelMap.addAttribute("error_otp", true);
			}
		}
		
		return "/login";
	}
}
