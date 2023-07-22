package com.babyshop.babyshop.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.babyshop.babyshop.models.Brand;
import com.babyshop.babyshop.models.Cart;
import com.babyshop.babyshop.models.CartItem;
import com.babyshop.babyshop.models.Category;
import com.babyshop.babyshop.models.Customer;
import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.models.Subcategory;
import com.babyshop.babyshop.models.User;
import com.babyshop.babyshop.service.BrandService;
import com.babyshop.babyshop.service.CategoryService;
import com.babyshop.babyshop.service.CookiesService;
import com.babyshop.babyshop.service.ProductService;
import com.babyshop.babyshop.service.SubcategoryService;
import com.babyshop.babyshop.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
	
	@Autowired
	CookiesService cookiesService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	UserService userService;
	
	public void loadData(ModelMap modelMap) {
		List<Category> categories = categoryService.getAll();
		Subcategory subcategoryR1 = subcategoryService.getByName("Dresses");
		Subcategory subcategoryR2 = subcategoryService.getByName("Coats & Jackets");
		List<Product> productsSale = productService.getProductBySale(1);
		List<Brand> brands = brandsService.getAll();
		List<String> favorites = favorite();
		String sizeCart = loadSizeCart();
		
		modelMap.addAttribute("categories", categories);
		modelMap.addAttribute("subcategoryR1", subcategoryR1);
		modelMap.addAttribute("subcategoryR2", subcategoryR2);
		modelMap.addAttribute("productsSale", productsSale);
		modelMap.addAttribute("brands", brands);
		modelMap.addAttribute("favorites", favorites);
		modelMap.addAttribute("sizeCart", sizeCart);
	}
	
	public void loadFilter(ModelMap modelMap) {
		List<String> sizes = loadSizes();
		List<String> ages = loadAge();
		List<String> colors = loadColors();
		List<String> genders = loadGenders();
		List<Subcategory> subcategories = subcategoryService.getAll();
		
		modelMap.addAttribute("sizes", sizes);
		modelMap.addAttribute("ages", ages);
		modelMap.addAttribute("colors", colors);
		modelMap.addAttribute("subcategories", subcategories);
		modelMap.addAttribute("genders", genders);
	}
	
	public List<String> loadSizes(){
		int count = 50;
		List<String> sizes = new ArrayList<>();
		sizes.add("One Size");
		while(true) {
			if(count>158) break;
			String size = count + " cm";
			sizes.add(size);
			count += 6;
		}
		count = 18;
		while(true) {
			if(count>36) break;
			String size = count + " EU";
			sizes.add(size);
			count++;
		}
		return sizes;
	}
	
	public List<String> loadAge(){
		List<String> ages = new ArrayList<>();
		ages.add("0-3 months");
		ages.add("3-6 months");
		ages.add("6-9 months");
		ages.add("9-12 months");
		int count = 1;
		while(true) {
			if(count>36) break;
			String age = count + " year";
			ages.add(age);
			count++;
		}
		return ages;
	}
	
	public List<String> loadColors(){
		List<String> colors = new ArrayList<>();
		colors.add("Beige");
		colors.add("Black");
		colors.add("Blue");
		colors.add("Brown");
		colors.add("Cream");
		colors.add("Green");
		colors.add("Grey");
		colors.add("Navy");
		colors.add("Orange");
		colors.add("Pink");
		colors.add("Purple");
		colors.add("Red");
		colors.add("Yellow");
		return colors;
	}
	
	public List<String> loadGenders(){
		List<String> genders = new ArrayList<>();
		genders.add("Boys");
		genders.add("Girls");
		genders.add("Unisex");
		return genders;
	}
	
	public List<String> favorite(){
		Cookie[] cookies = request.getCookies();
		String favorites = ""; //chuỗi favorite ở cookie
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("FAVORITE")) {
					favorites = cookie.getValue();
				}
			} 
		}
		List<String> favorite = Arrays.asList(favorites.split("C"));
		return favorite;
	}
	public String loadSizeCart() {
		User user = (User)session.getAttribute("user");
		if(user!=null) {
			user = userService.getUserById(user.getUserId());
			Customer customer = user.getCustomer();
			if(customer!=null) {
				Cart cart = customer.getCart();
				if(cart!=null) {
					List<CartItem> cartItems = cart.getCartItem();
					if(!cartItems.isEmpty()) {
						return cartItems.size() +"";
					}
				}
			}
			return 0 +"";
		}
		return null;
	}
}