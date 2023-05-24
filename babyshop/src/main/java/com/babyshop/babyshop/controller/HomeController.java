package com.babyshop.babyshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.babyshop.babyshop.models.Brands;
import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.service.BrandsService;
import com.babyshop.babyshop.service.ImageProductService;
import com.babyshop.babyshop.service.ProductService;

@Controller
@RequestMapping(path = "")
public class HomeController {
	@Autowired
	ImageProductService imageProductService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	BrandsService brandsService;
	
	@GetMapping("/")
	public String home(ModelMap modelMap) {
		List<Product> productsSale = productService.getProductBySale();
		List<Brands> brands = brandsService.getAll();
		List<Product> productsCategoryR1 = productService.getProductByCategory("Dresses");
		List<Product> productsCategoryR2 = productService.getProductByCategory("Coat & Jacket");
		
		modelMap.addAttribute("productsCategoryR1", productsCategoryR1);
		modelMap.addAttribute("productsCategoryR2", productsCategoryR2);
		modelMap.addAttribute("productsSale", productsSale);
		modelMap.addAttribute("brands", brands);
		return "home";
	}
}
