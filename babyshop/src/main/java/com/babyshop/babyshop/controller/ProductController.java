package com.babyshop.babyshop.controller;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.babyshop.babyshop.models.Feedback;
import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.models.Variant;
import com.babyshop.babyshop.service.CookiesService;
import com.babyshop.babyshop.service.FeedbackService;
import com.babyshop.babyshop.service.OrderDetailsService;
import com.babyshop.babyshop.service.ProductService;
import com.babyshop.babyshop.service.SubcategoryService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {
	@Autowired
	LoadDataController loadDataController;

	@Autowired
	SubcategoryService subcategoryService;

	@Autowired
	ProductService productService;

	@Autowired
	HttpSession session;

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpServletResponse response;

	@Autowired
	CookiesService cookiesService;
	
	@Autowired
	FeedbackService feedbackService;
	
	@Autowired
	OrderDetailsService orderDetailsService;

	@GetMapping("/search/product")
	public String search(ModelMap modelMap, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "productName", defaultValue = "") String productName,
			@RequestParam(name = "sort", defaultValue = "newIn") String sort,
			@RequestParam(name = "subcategory", defaultValue = "") List<Integer> subcategoriesChecked,
			@RequestParam(name = "ages", defaultValue = "") List<String> agesChecked,
			@RequestParam(name = "brands", defaultValue = "") List<Integer> brandsChecked,
			@RequestParam(name = "sizes", defaultValue = "") List<String> sizesChecked,
			@RequestParam(name = "colors", defaultValue = "") List<String> colorsChecked,
			@RequestParam(name = "genders", defaultValue = "") List<String> gendersChecked) {
		loadDataController.loadData(modelMap);
		loadDataController.loadFilter(modelMap);

		List<Product> products = productService.getByNameContain(productName);
		List<Product> oldProducts = productService.copy(products);

		List<Product> productsFilter = productService.filter(products, subcategoriesChecked, agesChecked, brandsChecked,
				sizesChecked, colorsChecked, gendersChecked);
		productService.sort(productsFilter, sort);

		List<Product> listProByPage;
		if ((page-1) * 16 < productsFilter.size()) {
			if (productsFilter.size() > page * 16 - 1) {
				listProByPage = productsFilter.subList((page-1) * 16, page* 16);
			} else {
				listProByPage = productsFilter.subList((page-1) * 16, productsFilter.size()); 
			}
		}else {
			listProByPage = productsFilter;
		}
		int maxPage = productsFilter.size()/16+1;
		session.setAttribute("products", listProByPage);
		session.setAttribute("oldProducts", oldProducts);
		modelMap.addAttribute("productName", productName);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("maxPage", maxPage);

		modelMap.addAttribute("sort", sort);
		modelMap.addAttribute("subcategoriesChecked", subcategoriesChecked);
		modelMap.addAttribute("agesChecked", agesChecked);
		modelMap.addAttribute("brandsChecked", brandsChecked);
		modelMap.addAttribute("sizesChecked", sizesChecked);
		modelMap.addAttribute("colorsChecked", colorsChecked);
		modelMap.addAttribute("gendersChecked", gendersChecked);
		return "search";
	}

	@PostMapping("/search/product/filter")
	public String filter(ModelMap modelMap, @RequestParam(name = "sort", defaultValue = "newIn") String sort,
			@RequestParam(name = "subcategory", defaultValue = "") List<Integer> subcategoriesChecked,
			@RequestParam(name = "ages", defaultValue = "") List<String> agesChecked,
			@RequestParam(name = "brands", defaultValue = "") List<Integer> brandsChecked,
			@RequestParam(name = "sizes", defaultValue = "") List<String> sizesChecked,
			@RequestParam(name = "colors", defaultValue = "") List<String> colorsChecked,
			@RequestParam(name = "genders", defaultValue = "") List<String> gendersChecked,
			@RequestParam(name = "productName", defaultValue = "") String productName) {
		loadDataController.loadData(modelMap);
		loadDataController.loadFilter(modelMap);

		List<Product> oldProducts = (List<Product>) session.getAttribute("oldProducts");

		List<Product> productsFilter = productService.filter(oldProducts, subcategoriesChecked, agesChecked,
				brandsChecked, sizesChecked, colorsChecked, gendersChecked);
		productService.sort(productsFilter, sort);
		
		 
		int page = 1;
		List<Product> listProByPage;
		if ((page-1) * 16 < productsFilter.size()) {
			if (productsFilter.size() > page * 16 - 1) {
				listProByPage = productsFilter.subList((page-1) * 16, page* 16);
			} else {
				listProByPage = productsFilter.subList((page-1) * 16, productsFilter.size()); 
			}
		}else {
			listProByPage = productsFilter;
			
		}
		int maxPage = productsFilter.size()/16+1;
		modelMap.addAttribute("page", 1);
		modelMap.addAttribute("maxPage", maxPage);
		
		session.setAttribute("products", listProByPage);

		return "search :: search-product";
	}

	@GetMapping("/product/details/{id}")
	public String detailsProduct(ModelMap modelMap, @PathVariable("id") int productId) {
		loadDataController.loadData(modelMap);
		Product product = productService.getProductById(productId);
		List<Feedback> feedbacks = feedbackService.getFeedBacksActive(product);
		List<Product> productsSimilar = product.getSubcategory().getProducts()
										.stream().filter(pro -> {
											return pro.getProductId()!= product.getProductId();
										}).toList();
		double avgSt =0;
		if(!feedbacks.isEmpty()) {
			avgSt = ((double)feedbacks.stream().mapToInt(fb ->fb.getRateStar())
												 .sum())/feedbacks.size();
		}
		DecimalFormat df = new DecimalFormat("#.##");
		String avgStar = df.format(avgSt);
		//System.out.println((int)(avgSt*100%100));
		
		int totalSold = orderDetailsService.getTotalProductSold(product);
		
		modelMap.addAttribute("product", product);
		modelMap.addAttribute("feedbacks", feedbacks);
		modelMap.addAttribute("productsSimilar", productsSimilar);
		modelMap.addAttribute("avgStar", avgStar);
		modelMap.addAttribute("totalSold", totalSold);
		return "detailsproduct";
	}

	@GetMapping("/product/favorite")
	public String favorite(ModelMap modelMap) { 
		loadDataController.loadData(modelMap);
		Cookie[] cookies = request.getCookies();
		String favorites = ""; // chuỗi favorite ở cookie
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("FAVORITE")) {
					favorites = cookie.getValue();
				}
			}
		}
		List<Product> products = cookiesService.getProductsById(favorites);

		modelMap.addAttribute("products", products);
		return "favorite";
	}

	@PutMapping("/product/favorite/{productId}")
	public String editFavorite(@PathVariable String productId) {
		Cookie[] cookies = request.getCookies();
		String favorites = ""; // chuỗi favorite ở cookie
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("FAVORITE")) {
					favorites = cookie.getValue();
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		String newFavorites = cookiesService.addOrRemoveProductId(favorites, productId);
		Cookie fvr = new Cookie("FAVORITE", newFavorites);
		fvr.setMaxAge(60 * 60 * 24 * 365);// 1 năm
		fvr.setPath("/");
		fvr.setDomain(request.getServerName());
		response.addCookie(fvr);
		return "favorite";
	}
}
