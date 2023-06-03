package com.babyshop.babyshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.babyshop.babyshop.models.Brand;
import com.babyshop.babyshop.models.Category;
import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.models.Subcategory;
import com.babyshop.babyshop.repositories.CategoryRepository;
import com.babyshop.babyshop.service.BrandService;
import com.babyshop.babyshop.service.CategoryService;
import com.babyshop.babyshop.service.ProductService;
import com.babyshop.babyshop.service.SubcategoryService;

@Controller
@RequestMapping(path = "")
public class HomeController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	BrandService brandsService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	SubcategoryService subcategoryService;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping("/")
	public String home(ModelMap modelMap) {
		List<Category> categories = categoryService.getAll();
		Subcategory subcategoryR1 = subcategoryService.getByName("Dresses");
		Subcategory subcategoryR2 = subcategoryService.getByName("Coats & Jackets");
		List<Product> productsSale = productService.getProductBySale(1);
		List<Brand> brands = brandsService.getAll();
		
		
		modelMap.addAttribute("categories", categories);
		modelMap.addAttribute("subcategoryR1", subcategoryR1);
		modelMap.addAttribute("subcategoryR2", subcategoryR2);
		modelMap.addAttribute("productsSale", productsSale);
		modelMap.addAttribute("brands", brands);
		return "home";
	}
}
