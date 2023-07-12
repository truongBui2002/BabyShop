package com.babyshop.babyshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.babyshop.babyshop.models.Image;
import com.babyshop.babyshop.models.User;
import com.babyshop.babyshop.service.ImageService;
import com.babyshop.babyshop.service.SendMailService;
import com.babyshop.babyshop.service.UserService;
import com.babyshop.babyshop.util.RandomKey;

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
	
	@Autowired
	ImageService imageService;

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
	
	@GetMapping("/forgotbyemail")
	public String forgotbyemail(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "forgotbyemail";
	}
	
	@GetMapping("/forgotbyphone")
	public String forgotbyphone(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "forgotbyphone";
	}

	@GetMapping("/user/viewprofile")
	public String viewProfile(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "viewprofile";
	}

	@GetMapping("/email")
	public String email(ModelMap modelMap) {
		
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
			@RequestParam(name = "password") String password, ModelMap modelMap) {
		User user = new User();

		user.setPhoneNumber(phoneOrMail);
		user.setPassword(password);
		userService.saveUser(user);

		return "redirect:/login?success";
	}
	
	@PostMapping("/user/update/profile")
	public String updateProfile(@RequestParam(name = "avatar") MultipartFile avatar,
			/*
			 * @RequestParam(name = "day") String day, @RequestParam(name = "month") String
			 * month,
			 */
			/* @RequestParam(name = "year") String year, */ @RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "address") String address, @RequestParam(name = "gender") String gender) {
		User user = (User)session.getAttribute("user");
		if(!avatar.isEmpty()) {
			 String imgName = imageService.updateAvatar(avatar);
			 if(user.getImage()!=null) {
				 user.getImage().setName(imgName);
			 }
			 else {
				 Image image = new Image(imgName);
				 user.setImage(image);
			 }
		}
		/*
		 * int newYear = Integer.parseInt(year); int newMonth = Integer.parseInt(month);
		 * int newDay = Integer.parseInt(day);
		 */
		/*
		 * Date date = new Date(newYear, newMonth, newDay); user.setDob(date);
		 */
		
		user.setFullName(firstName);
		if(gender.equals("1")) {
			user.setGender(true);
		}else {
			user.setGender(false);
		}
		userService.saveUser(user);

		return "redirect:/user/viewprofile";
	}
	
	@GetMapping("/user/update/email")
	public String updateEmail(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		
		return "email";
	}
	
	@GetMapping("/user/resetpass")
	public String resetPass(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "resetpass";
	}
	
	@PostMapping("/user/update/password")
	public String updatePass(ModelMap modelMap,
			@RequestParam(name = "password")String password, 
			@RequestParam(name = "repass") String repass) {
		loadDataController.loadData(modelMap);
		if(password.equals(repass)) {
			User user = (User)session.getAttribute("user");
			user.setPassword(password);
			userService.saveUser(user);
			return "redirect:/user/resetpass?success";
		}else {
			modelMap.addAttribute("err", true);
			return "redirect:/user/resetpass?err";
		}
	}
	
	@PostMapping("/fogotpass/email")
	public String fogotByEmail(ModelMap modelMap, @RequestParam(name = "email") String email) {
		loadDataController.loadData(modelMap);
		User user = userService.findByEmail(email);
		if(user!=null) {
			RandomKey rd = new RandomKey();
			String pass = rd.getString(10);
			sendMailService.sendNewPass(email, pass);
			user.setPassword(pass);
			userService.save(user);
			modelMap.addAttribute("success", "Password sent to your email!!!");
		}else {
			modelMap.addAttribute("err", "Email does not exist!!!");
		}
		return "forgotbyemail";
	}
	
	@GetMapping("/user/viewprofile/address")
	public String changeAddress(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "/profile/changeaddress";
	}
	
	@GetMapping("/user/viewprofile/changepass")
	public String changepass(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "/profile/changepass";
	}
	
	@GetMapping("/user/viewprofile/payment")
	public String proPayment(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "/profile/profilepayment";
	}
	
	@GetMapping("/user/viewprofile/noti")
	public String proNoti(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "/profile/notification";
	}

	@GetMapping("/user/viewprofile/order/orderhistory")
	public String orderhistory(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "/order/orderhistory";
	}
}
