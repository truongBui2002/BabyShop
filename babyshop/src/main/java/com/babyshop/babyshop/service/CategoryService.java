package com.babyshop.babyshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.babyshop.babyshop.controller.ImageController;
import com.babyshop.babyshop.models.Brand;
import com.babyshop.babyshop.models.Category;
import com.babyshop.babyshop.models.Image;
import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.models.Subcategory;
import com.babyshop.babyshop.repositories.CategoryRepository;
import com.babyshop.babyshop.repositories.ImageRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	BrandService brandService;

	public List<Category> getAll() {
		List<Category> categories = categoryRepository.findAll();
		for (Category category : categories) {
			addLinkImage(category);
		}
		return categories;
	}

	public Category getById(int id) {
		Category category = categoryRepository.findById(id).get();
		addLinkImage(category);
		return category;
	}

	public void addLinkImage(Category category) {
		Image image = category.getImage();
		entityManager.detach(image);
		Image img = imageRepository.findById(image.getImageId()).get();
		String imageName = MvcUriComponentsBuilder
				.fromMethodName(ImageController.class, "readDetailFileCategory", img.getName()).build().toUri()
				.toString();

		image.setName(imageName);

		category.setImage(image);
	}

	public List<Brand> getBrands(Category category) {
		List<Brand> brands = new ArrayList<>();
		List<Subcategory> subcategories = category.getSubcategories();

		for (Subcategory subcategory : subcategories) {
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
		}
		for (Brand brand : brands) {
			brandService.addLinkImage(brand);
		}
		return brands;
	}

	public List<Product> getProducts(Category category) {
		List<Product> products = new ArrayList<>();
		for (Subcategory subcate : category.getSubcategories()) {
			
			for (Product product : subcate.getProducts()) {
				boolean checkExist = true;
				for (Product prNew : products) {
					if(prNew.getProductId()==product.getProductId()) {
						checkExist = false;
					}
				}
				if (checkExist) {
					products.add(product);
				}
			}
		}
		return products;
	}
}
