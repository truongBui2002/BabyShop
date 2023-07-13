package com.babyshop.babyshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babyshop.babyshop.models.Brand;
import com.babyshop.babyshop.repositories.BrandRepository;
import com.babyshop.babyshop.repositories.ImageRepository;


@Service
public class BrandService {
	
	@Autowired 
	BrandRepository brandsRepository;
	
	@Autowired
	ImageRepository imageRepository;
	//lấy ra toàn bộ thương hiệu
	public List<Brand> getAll() {
		List<Brand> brands = brandsRepository.findAll();
		return brands;
	}
	
	public Brand getBrandById(int brandId) {
		Optional<Brand> brand = brandsRepository.findById(brandId);
		if(brand.isPresent()) {
			return brand.get();
		}
		return null;
	}
	
}
