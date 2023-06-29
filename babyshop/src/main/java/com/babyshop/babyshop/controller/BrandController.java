package com.babyshop.babyshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.babyshop.babyshop.models.Brand;
import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.service.BrandService;
import com.babyshop.babyshop.service.ProductService;
import com.babyshop.babyshop.service.SubcategoryService;

@Controller
public class BrandController {
	@Autowired
	private LoadDataController loadDataController;
	
	@Autowired
	BrandService brandService;
	
	@Autowired
	SubcategoryService subcategoryService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/brand")
	public String brand(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		List<Brand> brands = brandService.getAll();
		List<Character> alphabet = new ArrayList<>();
		for (int i = 65; i <= 90; i++) {
			boolean checkExist = false;
			for (Brand brand : brands) {
				if(brand.getName().startsWith((char)(i) + "")) {
					checkExist = true;
				}
			}
			if(checkExist) {
				alphabet.add((char)(i));
			}
		}
		modelMap.addAttribute("brands", brands);
		modelMap.addAttribute("alphabet", alphabet);
 		return "brand";
	}
	
	@GetMapping("/brand/details/{brandId}")
	public String detailBrand(ModelMap modelMap, @PathVariable("brandId") int brandId) {
		loadDataController.loadData(modelMap);
		Brand brand = brandService.getBrandById(brandId);
		List<Product> products = productService.getProductByBrand(brand);
		
		modelMap.addAttribute("brand", brand);
		modelMap.addAttribute("products", products);
		return "detailbrand";
	}
}
