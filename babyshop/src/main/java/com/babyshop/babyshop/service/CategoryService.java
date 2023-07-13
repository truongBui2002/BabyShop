package com.babyshop.babyshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babyshop.babyshop.models.Brand;
import com.babyshop.babyshop.models.Category;
import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.models.Subcategory;
import com.babyshop.babyshop.repositories.CategoryRepository;
import com.babyshop.babyshop.repositories.ImageRepository;


@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ImageRepository imageRepository;

	@Autowired
	BrandService brandService;

	public List<Category> getAll() {
		List<Category> categories = categoryRepository.findAll();
		return categories;
	}

	public Category getById(int id) {
		Category category = categoryRepository.findById(id).get();
		return category;
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
