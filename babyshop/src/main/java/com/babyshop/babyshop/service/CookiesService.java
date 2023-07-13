package com.babyshop.babyshop.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babyshop.babyshop.models.Product;

@Service
public class CookiesService {
	@Autowired
	ProductService productService;

	public List<Product> getProductsById(String productsId) {
		if (productsId.equals("")) {
			return new ArrayList<>();
		}
		List<Product> products = new ArrayList<>();
		String[] prsId = productsId.split("C");
		for (String pid : prsId) {
			Product p = null;
			if (!pid.equals("")) {
				p = productService.getProductById(Integer.parseInt(pid));
			}
			if (p != null) {
				products.add(p);
			}
		}
		return products;
	}

	public String addOrRemoveProductId(String productsId, String productId) {
		// Nếu productsId ="" tức là list favorite chưa tồn tại
		// Do đó chắc chắn là thêm sản phảm
		if (productsId.equals("")) {
			return "C" + productId;
		}
		List<String> prsId = Arrays.asList(productsId.split("C"));
		if (prsId.contains(productId)) {
			for (String pId : prsId) {
				if (pId.equals(productId)) {
					String pattern = "C" + pId + "(?!\\d)"; // theo sau không được là một số
					Pattern regex = Pattern.compile(pattern);
					productsId = regex.matcher(productsId).replaceAll("");
				}
			}
		} else {
			productsId += "C" + productId;
		}

		return productsId;
	}

	public String addProductId(String productsId, String productId) {
		// TODO
		return productsId;
	}

	public String removeProductId(String productsId, String productId) {
		// TODO
		return productsId;
	}
}
