package com.babyshop.babyshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babyshop.babyshop.models.ImageProduct;
import com.babyshop.babyshop.repositories.ImageProductRepository;

@Service
public class ImageProductService {
	@Autowired
	ImageProductRepository imageProductRepository;
	
	public List<ImageProduct> getByProductId(int productId){
		
		return imageProductRepository.getByProductId(productId);
	}
}
