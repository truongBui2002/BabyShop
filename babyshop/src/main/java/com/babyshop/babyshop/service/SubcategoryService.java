package com.babyshop.babyshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.babyshop.babyshop.controller.ImageController;
import com.babyshop.babyshop.models.Brand;
import com.babyshop.babyshop.models.Image;
import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.models.Subcategory;
import com.babyshop.babyshop.repositories.ImageRepository;
import com.babyshop.babyshop.repositories.SubcategoryRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class SubcategoryService {

	@Autowired
	SubcategoryRepository subcategoryRepository;

	@Autowired
	ProductService productService;

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	CategoryService categoriesService;

	@PersistenceContext // Tự động tạo ra EntityManager và cấu hình vào Spring
	EntityManager entityManager;

	public List<Subcategory> getAll() {
		return subcategoryRepository.findAll();
	}

	public Subcategory getByName(String name) {
		Subcategory subcategory = subcategoryRepository.findByName(name);
		addLinkImage(subcategory);
		List<Product> products = subcategory.getProducts();
		for (Product product : products) {
			productService.addLinkImage(product);
		}
		return subcategory;
	}

	public Subcategory getById(int id) {
		Subcategory subcategory = subcategoryRepository.findById(id).get();
		addLinkImage(subcategory);
		return subcategory;
	}

	public List<Brand> getBrands(Subcategory subcategory) {
		List<Brand> brands = new ArrayList<>();
		for (Product product : subcategory.getProducts()) {
			boolean checkExist = true;
			for (Brand brand : brands) {
				if (brand.getBrandId() == product.getBrand().getBrandId()) {
					checkExist = false;
				}
			}
			if (checkExist) {
				brands.add(product.getBrand());
			}
		}
		return brands;
	}

	public void addLinkImage(Subcategory subcategory) {
		if (subcategory.getImage() == null)
			return;
		Image image = subcategory.getImage();
		entityManager.detach(image);
		Image img = imageRepository.findById(image.getImageId()).get();
		String imageName = MvcUriComponentsBuilder
				.fromMethodName(ImageController.class, "readDetailFileSubcategory", img.getName()).build().toUri()
				.toString();

		image.setName(imageName);
		subcategory.setImage(image);
	}
}
