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

@Service
public class BrandService {
	
	@Autowired 
	BrandRepository brandsRepository;
	
	//lấy ra toàn bộ thương hiệu
	public List<Brand> getAll() {
		//lấy ra những sản phẩm giảm giá
		List<Brand> brands = brandsRepository.findAll();
		for (Brand brand : brands) {
			brand = addLinkImage(brand);
		}
		return brands;
	}
	
	public Brand addLinkImage(Brand brand) {
		Image image = brand.getImage();
		String imageName = MvcUriComponentsBuilder.fromMethodName(ImageController.class,
                "readDetailFileBrand", image.getName()).build().toUri().toString();
		image.setName(imageName);
		return brand;
	}
}
