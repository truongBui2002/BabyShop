package com.babyshop.babyshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.service.BrandService;
import com.babyshop.babyshop.service.ProductService;
import com.babyshop.babyshop.service.SubcategoryService;

@Controller
@RequestMapping(path = "")
public class HomeController {
	@Autowired
	private LoadDataController loadDataController;
	
	@Autowired
	BrandService brandService;
	
	@Autowired
	SubcategoryService subcategoryService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/")
	public String home(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "home";
	}
	@GetMapping("/categories")
	public String categories(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "categories";

	}
	@GetMapping("/subcategories")
	public String subCategories(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		List<Product> products = productService.getAll();
		
		modelMap.addAttribute("products", products);
		return "subcategories";

	}
	@GetMapping("/afterresetbyphone")
	public String afterresetpass() {
		
		return "afterresetbyphone";

	}
}
