package com.babyshop.babyshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babyshop.babyshop.models.Categories;
import com.babyshop.babyshop.repositories.CategoriesRepository;

@Service
public class CategoriesService {
	@Autowired
	CategoriesRepository categoriesRepository;
	
	public Categories getByProductId(int productId){
		Categories categories = categoriesRepository.findByProductId(productId);
		return categories;
	}
	
	public List<Categories> getByCategoryName(String categoryName){
		List<Categories> categories = categoriesRepository.findByCategoryName(categoryName);
		return categories;
	}
}
