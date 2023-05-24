package com.babyshop.babyshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.babyshop.babyshop.controller.ImageController;
import com.babyshop.babyshop.models.Categories;
import com.babyshop.babyshop.models.Image;
import com.babyshop.babyshop.models.ImageProduct;
import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.repositories.ProductRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ImageService imageService;

	@Autowired
	ImageProductService imageProductService;

	@Autowired
	CategoriesService categoriesService;
	
	@PersistenceContext
	EntityManager entityManager;
	
	// Lấy ra toàn bộ sản phẩm
	public List<Product> getAll() {

		return productRepository.findAll();
	}
	
	//Lấy ra những sản phẩm đang giảm giá (<1)
	public List<Product> getProductBySale() {
		//lấy ra những sản phẩm giảm giá
		List<Product> products = productRepository.findProductsByDiscount();
		products = addImageToProducts(products);
		return products;
	}
	
	//lấy ra những sản phẩm được phân loại theo category
	public List<Product> getProductByCategory(String categoryName) {
		//
		List<Categories> categories = categoriesService.getByCategoryName(categoryName);
		List<Product> products = new ArrayList<>();
		if (categories != null) {
			for (Categories category : categories) {
//				System.out.println("CategoryID: " + category.getProductId());
				Product product = productRepository.findById(category.getProductId()).get();
				product.setCategoryName(categoryName);
//				System.out.println("Product: " + product.toString());
				products.add(product);
			}
			products = addImageToProducts(products);
			
		}
		
		return products;
	}
	
	//Thêm ảnh vào sản phẩm
	public List<Product> addImageToProducts(List<Product> products){
		List<ImageProduct> listImgProduct = new ArrayList<>();
		//lấy ra toàn bộ ảnh có productId
		for (Product product : products) {
			listImgProduct.addAll(imageProductService.getByProductId(product.getProductId()));
		}
		//Thêm ảnh vào sản phẩm
		int countP = 0;
		int countImg = 0;
		for (Product product : products) {
			countP++;
			for (ImageProduct img : listImgProduct) {
				if (product.getProductId() == img.getProductId()) {
					countImg++;
					List<Image> images = product.getImages();
					//Xóa những đối tượng Image đã tồn tại trong cache
					entityManager.detach(imageService.getImageById(img.getImageId())) ;
					Image image = imageService.getImageById(img.getImageId());
					String fileName = image.getImageName();
					String uriImage = getURIImage(fileName);
					image.setImageName(uriImage);
					images.add(image);
				}
			} 
		}
		//Xóa những sản phẩm không có ảnh
		products.removeIf(img -> img.getImages().size() == 0);
		return products;
	}
	
	//Tạo đường dẫn tuyệt đối đến ảnh
	public String getURIImage(String fileName) {
		return MvcUriComponentsBuilder
		.fromMethodName(ImageController.class, "readDetailFileProduct", fileName).build().toUri()
		.toString();
	}
 
}
