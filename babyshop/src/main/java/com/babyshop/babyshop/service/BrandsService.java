package com.babyshop.babyshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.babyshop.babyshop.controller.ImageController;
import com.babyshop.babyshop.models.Brands;
import com.babyshop.babyshop.models.Image;
import com.babyshop.babyshop.repositories.BrandsRepository;

@Service
public class BrandsService {
	
	@Autowired 
	BrandsRepository brandsRepository;
	
	@Autowired
	ImageService imageService;
	//lấy ra toàn bộ thương hiệu
	public List<Brands> getAll(){
		List<Brands> list = brandsRepository.findAll();
		for (Brands brand : list) {
			Image image = imageService.getImageById(brand.getImageId());
			String fileName = image.getImageName();
			String uriName = MvcUriComponentsBuilder.fromMethodName(ImageController.class,
                    "readDetailFileBrand", fileName).build().toUri().toString();
			brand.setImageName(uriName);
		}
		return list;
	}
	
	
	
}
