package com.babyshop.babyshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.babyshop.babyshop.models.Brand;
import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.models.Subcategory;
import com.babyshop.babyshop.service.ProductService;
import com.babyshop.babyshop.service.SubcategoryService;

@Controller
public class SubcategoryController {
	@Autowired
	LoadDataController loadDataController;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	SubcategoryService subcategoryService;
	
	@GetMapping("/subcategories/{subcategoryId}")
	public String subCategories(ModelMap modelMap, @PathVariable("subcategoryId") int id) {
		loadDataController.loadData(modelMap);
		Subcategory subdetails = subcategoryService.getById(id);
		List<Brand> brandsSub = subcategoryService.getBrands(subdetails);
		modelMap.addAttribute("subdetails", subdetails);
		modelMap.addAttribute("brandsSub", brandsSub);
		
		return "subcategories";

	}
}
