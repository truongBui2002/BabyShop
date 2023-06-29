package com.babyshop.babyshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.service.ProductService;
import com.babyshop.babyshop.service.SubcategoryService;


import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {
	private final String PRODUCTS = "products"; //Tên list product được truyền đi 
	@Autowired
	LoadDataController loadDataController;
	
	@Autowired
	SubcategoryService subcategoryService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/search/product")
	public String search(ModelMap modelMap, 
			HttpSession session,
			@RequestParam(name = "productName", defaultValue = "") String productName,
			@RequestParam(name = "sort", defaultValue = "newIn") String sort) {
		loadDataController.loadData(modelMap);
		loadDataController.loadFilter(modelMap);
		List<Product> products = productService.getByNameContain(productName);
		
		modelMap.addAttribute("sort", sort);
		session.setAttribute(PRODUCTS, products); 
		modelMap.addAttribute("productName", productName);
		return "search";
	}
	@GetMapping("/search/product/filter")
	public String filter(ModelMap modelMap, HttpSession session,  
			@RequestParam(name = "sort", defaultValue = "") String sort,
			@RequestParam(name = "subcategory", defaultValue = "") List<Integer> subcategoriesChecked,
			@RequestParam(name = "ages", defaultValue = "") List<String> agesChecked,
			@RequestParam(name = "brands", defaultValue = "") List<Integer> brandsChecked,
			@RequestParam(name = "sizes", defaultValue = "") List<String> sizesChecked,
			@RequestParam(name = "colors", defaultValue = "") List<String> colorsChecked,
			@RequestParam(name = "genders", defaultValue = "") List<String> gendersChecked,
			@RequestParam(name = "search", defaultValue = "") String search
			) {
		//Nếu người dùng truy cập trực tiếp tới đường dẫn này thì chuyển tới trang tìm kiếm
		if(session.getAttribute(PRODUCTS)==null) return "redirect:/search/product";
		
		loadDataController.loadData(modelMap);
		loadDataController.loadFilter(modelMap);
		 
		List<Product> products = (List<Product>)session.getAttribute(PRODUCTS);
		
		//Ở search.html có một input hidden, khi user search trên thanh tìm kiếm thì giá trị của input sẽ thiết lập 
		//mặc đinh sẽ là "" 
		if(search.equals("") 
				|| (subcategoriesChecked.size() == 0 || agesChecked.size()==0 || brandsChecked.size()==0
				|| sizesChecked.size()== 0 || colorsChecked.size()== 0 || gendersChecked.size() ==0 )) {
			List<Product> productsFilter = productService.filter(subcategoriesChecked, agesChecked, brandsChecked, sizesChecked, colorsChecked, gendersChecked);
			productService.sort(productsFilter, sort);
			session.setAttribute(PRODUCTS, productsFilter); 
		}else {
			productService.sort(products, sort);
			session.setAttribute(PRODUCTS, products); 
		}
		
		modelMap.addAttribute("sort", sort);
		modelMap.addAttribute("subcategoriesChecked", subcategoriesChecked);
		modelMap.addAttribute("agesChecked", agesChecked);
		modelMap.addAttribute("brandsChecked", brandsChecked);
		modelMap.addAttribute("sizesChecked", sizesChecked);
		modelMap.addAttribute("colorsChecked", colorsChecked);
		modelMap.addAttribute("gendersChecked", gendersChecked);
		return "search";
	}
	@GetMapping("/product/details/{id}")
	public String detailsProduct(ModelMap modelMap, @PathVariable("id") int productId){
		loadDataController.loadData(modelMap);
		Product product = productService.getProductById(productId);
		modelMap.addAttribute("product", product);
		return "detailsproduct";
	}
	@GetMapping("/product/favorite")
	public String favorite(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "favorite";
	}
	
}
