package com.babyshop.babyshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.babyshop.babyshop.models.Brands;
import com.babyshop.babyshop.models.Image;
import com.babyshop.babyshop.models.ImageProduct;
import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.repositories.ImageProductRepository;
import com.babyshop.babyshop.repositories.ImageRepository;
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
		List<Product> listSale = productService.getProductBySale();
		List<Brands> listBrands = brandsService.getAll();
		modelMap.addAttribute("listSale", listSale);
		modelMap.addAttribute("listBrands", listBrands);
		return "home";
	}
}
