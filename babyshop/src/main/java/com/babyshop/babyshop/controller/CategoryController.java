package com.babyshop.babyshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.babyshop.babyshop.models.Brand;
import com.babyshop.babyshop.models.Category;
import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.models.Subcategory;
import com.babyshop.babyshop.service.CategoryService;
import com.babyshop.babyshop.service.ProductService;
import com.babyshop.babyshop.service.SubcategoryService;

@Controller
public class CategoryController {
	@Autowired
	LoadDataController loadDataController;

	@Autowired
	CategoryService categoryService;

	@Autowired
	SubcategoryService subcategoryService;
	
	@Autowired
	ProductService productService;

	@GetMapping("/categories/{categoryId}")
	public String categories(ModelMap modelMap, @PathVariable("categoryId") int id) {
		loadDataController.loadData(modelMap);
		Category category = categoryService.getById(id);
		
		for (Subcategory sub : category.getSubcategories()) {
			subcategoryService.addLinkImage(sub);
		}
		List<Subcategory> subs = new ArrayList<>();
		for (Subcategory subcate : category.getSubcategories()) {
			if(subcate.getImage()!=null) {
				subs.add(subcate);
			}
		}
		List<Brand> brandsCa = categoryService.getBrands(category);
		
		List<Product> prodCate = categoryService.getProducts(category);
		for (Product product : prodCate) {
			productService.addLinkImage(product);
		}
		
		modelMap.addAttribute("category", category);
		modelMap.addAttribute("subs", subs);
		modelMap.addAttribute("brandsCa", brandsCa);
		modelMap.addAttribute("prodCate", prodCate);
		return "categories";

	}
}
