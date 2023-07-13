package com.babyshop.babyshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;

import com.babyshop.babyshop.service.CookiesService;

@Controller
public class CartController {
	@Autowired
	CookiesService cookiesService;
	
	@PutMapping("/product/cart")
	public String addToCart() {
		System.out.println("addToCart");
		return "cart";
	}
}
