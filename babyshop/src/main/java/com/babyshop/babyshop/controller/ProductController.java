package com.babyshop.babyshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.repositories.ProductRepository;

@Controller
@RequestMapping("/home12345")
public class ProductController {
//	@Autowired
//	ProductRepository repository;
//	
//	@GetMapping("/all")
//	public List<Product> getAllProduct() {
////		Product p = new Product("ao", 20.0, "Ao dep", "Ao xau", 12, 12);
////		repository.save(p); 
//		return repository.findAll();
//	}
//	
//	@GetMapping("")
//	public String home() {
//		return "home";
//	} 
}
