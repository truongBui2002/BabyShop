package com.babyshop.babyshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.babyshop.babyshop.controller.ImageController;
import com.babyshop.babyshop.models.Image;
import com.babyshop.babyshop.models.ImageProduct;
import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.repositories.ImageRepository;
import com.babyshop.babyshop.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ImageService imageService;
	
	@Autowired
	ImageProductService imageProductService;
	
	//Lấy ra toàn bộ sản phẩm
	public List<Product> getAll(){
		
		return productRepository.findAll();
	}
	
	public List<Product> getProductBySale(){
		List<Product> list = productRepository.findProductsByDiscount();
		List<ImageProduct> listImgProduct = new ArrayList<>();
		for (Product product : list) {
			listImgProduct.addAll(imageProductService.getByProductId(product.getProductId()));
		}
		
		for (ImageProduct img : listImgProduct) {
			for (Product product : list) {
				if(product.getProductId() == img.getProductId()) {
					List<Image> images = product.getImages();
					Image image = imageService.getImageById(img.getImageId());
					String fileName = image.getImageName();
					String uriImage = MvcUriComponentsBuilder.fromMethodName(ImageController.class,
                            "readDetailFileProduct", fileName).build().toUri().toString();
					image.setImageName(uriImage);
					images.add(image);
				}
			}
		}

		return list;
	}
	
	
} 
