package com.babyshop.babyshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	@GetMapping("/admin/index")
	public String admin() {
		
		return "dashboard/index";
	}
	
	@GetMapping("/admin/user-account-setting")
	public String userSetting() {
		
		return "dashboard/user-account-setting";
	}
	@GetMapping("/admin/user-add")
	public String userAdd() {
		
		return "dashboard/user-add";
	}
	
	@GetMapping("/admin/user-list")
	public String userList() {
		
		return "dashboard/user-list";
	}
	@GetMapping("/admin/user-profile")
	public String userProfile() {
		
		return "dashboard/user-profile";
	}
	
	@GetMapping("/admin/orders")
	public String orders() {
		
		return "dashboard/orders";
	}
	
	@GetMapping("/admin/feedback")
	public String feedback() {
		
		return "dashboard/feedbacklist";
	}
	
	@GetMapping("/admin/list-product")
	public String listProduct() {
		
		return "dashboard/list-product";
	}
}
