package com.babyshop.babyshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.babyshop.babyshop.models.Image;
import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.service.BrandService;
import com.babyshop.babyshop.service.ImageService;
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
	
	@Autowired
	ImageService imageService;

	@GetMapping("/")
	public String home(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "home";
	}

	@GetMapping("/afterresetbyphone")
	public String afterresetpass() {
//		Product product = productService.getProductById(2);
//		product.getImages().clear();
//		List<Image> images = product.getImages();
//		images.add(new Image("abc123"));
//		images.add(new Image("abc456"));
//		images.add(new Image("abc789"));
//		
//		product.setImages(images);
//		
//		productService.save(product);
//		product = productService.getProductById(2);
//		for (Image image : product.getImages()) {
//			System.out.println(image.getName());
//		}
		return "afterresetbyphone";

	}

	@GetMapping("/error/e1")
	public String errorE1() {
		return "error/fourE";
	}
}
