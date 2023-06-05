package com.babyshop.babyshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.babyshop.babyshop.models.Brand;
import com.babyshop.babyshop.models.Category;
import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.models.Subcategory;
import com.babyshop.babyshop.service.BrandService;
import com.babyshop.babyshop.service.CategoryService;
import com.babyshop.babyshop.service.ProductService;
import com.babyshop.babyshop.service.SubcategoryService;

@Component
public class LoadDataController {
	@Autowired
	ProductService productService;
	
	@Autowired
	BrandService brandsService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	SubcategoryService subcategoryService;
	
	public void loadData(ModelMap modelMap) {
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
	}
}