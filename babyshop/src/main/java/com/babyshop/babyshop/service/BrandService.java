package com.babyshop.babyshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.babyshop.babyshop.controller.ImageController;
import com.babyshop.babyshop.models.Brand;
import com.babyshop.babyshop.models.Image;
//import com.babyshop.babyshop.repositories.BrandRepository;
import com.babyshop.babyshop.repositories.BrandRepository;
import com.babyshop.babyshop.repositories.ImageRepository;

import jakarta.persistence.EntityManager;

@Service
public class BrandService {
	
	@Autowired 
	BrandRepository brandsRepository;
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	ImageRepository imageRepository;
	//lấy ra toàn bộ thương hiệu
	public List<Brand> getAll() {
		List<Brand> brands = brandsRepository.findAll();
		for (Brand brand : brands) {
			addLinkImage(brand);
		}
		return brands;
	}
	
	public void addLinkImage(Brand brand) {
		Image image = brand.getImage();
		entityManager.detach(image);
		Image img = imageRepository.findById(image.getImageId()).get();
		String imageName = MvcUriComponentsBuilder.fromMethodName(ImageController.class,
                "readDetailFileBrand", img.getName()).build().toUri().toString();
		image.setName(imageName);
	}
	public Brand getBrandById(int brandId) {
		Optional<Brand> brand = brandsRepository.findById(brandId);
		if(brand.isPresent()) {
			addLinkImage(brand.get());
			return brand.get();
		}
		return null;
	}
	
}
