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
	private LoadDataController loadDataController;
	
	@GetMapping("/")
	public String home(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "home";
	}
}
