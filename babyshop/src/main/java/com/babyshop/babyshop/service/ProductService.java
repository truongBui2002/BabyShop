package com.babyshop.babyshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.babyshop.babyshop.controller.ImageController;
import com.babyshop.babyshop.models.Brand;
import com.babyshop.babyshop.models.Category;
import com.babyshop.babyshop.models.Image;
import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.repositories.ImageRepository;
//import com.babyshop.babyshop.repositories.ProductRepository;
import com.babyshop.babyshop.repositories.ProductRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	CategoryService categoriesService;
	
	@PersistenceContext // Tự động tạo ra EntityManager và cấu hình vào Spring
	EntityManager entityManager;
	
	//Lấy ra những sản phẩm đang giảm giá (<1)
	public List<Product> getProductBySale(double discount) {
		//lấy ra những sản phẩm giảm giá
		List<Product> products = productRepository.findByDiscountLessThan(discount);
		for (Product product : products) {
			product = addLinkImage(product);
		}
		return products;
	}
	
	public Product addLinkImage(Product product) {
		List<Image> images = new ArrayList<>();
		for (Image image : product.getImages()) {
			//Loại bỏ những image đã được JPA tạo ra
			entityManager.detach(image);
			Image img = imageRepository.findById(image.getImageId()).get();
			String imageName = MvcUriComponentsBuilder.fromMethodName(ImageController.class,
                    "readDetailFileProduct", img.getName()).build().toUri().toString();

			image.setName(imageName);
			images.add(image);
		}
		product.setImages(images);
		return product;
	}
}
