package com.babyshop.babyshop.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babyshop.babyshop.models.Brand;
import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.models.Variant;
import com.babyshop.babyshop.repositories.ImageRepository;
import com.babyshop.babyshop.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	CategoryService categoriesService;

	// Lấy ra những sản phẩm đang giảm giá (<1)
	public List<Product> getProductBySale(double discount) {
		// lấy ra những sản phẩm giảm giá
		List<Product> products = productRepository.findByDiscountLessThan(discount);
		return products;
	}


	public List<Product> getByNameContain(String name) {
		String[] arrWordName = name.split("\\s+");
		String nameFormat = "";
		for (String word : arrWordName) {
			nameFormat += word + " ";
		}
		List<Product> products = productRepository.findByNameContain(nameFormat.trim());
		return products;
	}

	public void sort(List<Product> products, String type) {
		if (type.equals(""))
			return;

		switch (type) {
		case "newIn":
			sortByDate(products, type);
			break;
		case "oldest":
			sortByDate(products, type);
			break;
		// a-z
		case "productNameDesc":
			sortByName(products, type);
			break;
		// z-a
		case "productNameAsc":
			sortByName(products, type);
			break;
		case "priceAsc":
			sortByPrice(products, type);
			break;
		case "priceDesc":
			sortByPrice(products, type);
			break;
		case "bestsellers":
			sortByBestsellers(products, type);
			break;
		// a-z
		case "brandNameDesc":
			sortByBrandName(products, type);
			break;
		// z-a
		case "brandNameAsc":
			sortByBrandName(products, type);
			break;
		}
	}

	public void sortByDate(List<Product> products, String type) {
		Collections.sort(products, new Comparator<Product>() {
			@Override
			public int compare(Product p1, Product p2) {
				return p2.getCreatedAt().compareTo(p1.getCreatedAt());
			}
		});
		if (!type.equals("newIn")) {
			Collections.reverse(products);
		}
	}

	public void sortByName(List<Product> products, String type) {
		Collections.sort(products, new Comparator<Product>() {
			@Override
			public int compare(Product p1, Product p2) {
				return p1.getName().compareTo(p2.getName());
			}
		});
		if (!type.equals("productNameDesc")) {
			Collections.reverse(products);
		}
	}

	public void sortByPrice(List<Product> products, String type) {
		Collections.sort(products, new Comparator<Product>() {
			@Override
			public int compare(Product p1, Product p2) {
				return p1.getPrice() - p2.getPrice();
			}
		});
		if (!type.equals("priceAsc")) {
			Collections.reverse(products);
		}
	}

	// TODO
	public void sortByBestsellers(List<Product> products, String type) {

	}

	public void sortByBrandName(List<Product> products, String type) {
		Collections.sort(products, new Comparator<Product>() {
			@Override
			public int compare(Product p1, Product p2) {
				return p1.getBrand().getName().compareTo(p2.getBrand().getName());
			}
		});
		if (!type.equals("brandNameDesc")) {
			Collections.reverse(products);
		}
	}

	public List<Product> filter(List<Product> oldProducts, List<Integer> subcategoriesChecked, List<String> agesChecked,
			List<Integer> brandsChecked, List<String> sizesChecked, List<String> colorsChecked,
			List<String> gendersChecked) {
		// Lọc ra những sản phẩm có subcategories
		List<Product> subcategoriesFilter = oldProducts.stream().filter(p -> {
			return subcategoriesChecked.size() != 0
					? subcategoriesChecked.contains(p.getSubcategory().getSubcategoryId())
					: true;
		}).toList();

		// Lọc ra những sản phẩm có ages trong subcategoriesFilter
		List<Product> agesFilter = subcategoriesFilter.stream().filter(p -> {
			return agesChecked.size() != 0 ? agesChecked.contains(p.getProductInfo().getAgeRange()) : true;
		}).toList();

		// Lọc ra những sản phẩm có brands trong agesFilter
		List<Product> brandsFilter = agesFilter.stream().filter(p -> {
			return brandsChecked.size() != 0 ? brandsChecked.contains(p.getBrand().getBrandId()) : true;
		}).toList();

		// Loc ra những sản phẩm có sizes trong brandsFilter
		List<Product> sizesFilter = brandsFilter.stream().filter(p -> {
			if (sizesChecked.size() != 0) {
				for (Variant var : p.getVariants()) {
					if (sizesChecked.contains(var.getName())) {
						return true;
					}
				}
				return false;
			} else {
				return true;
			}
		}).toList();

		// Lọc ra những sản phẩm có colors trong sizesFilter
		List<Product> colorsFilter = sizesFilter.stream().filter(p -> {
			return colorsChecked.size() != 0 ? colorsChecked.contains(p.getProductInfo().getColor()) : true;
		}).toList();

		// Lọc ra những sản phẩm có genders trong colorsFilter
		List<Product> gendersFilter = colorsFilter.stream().filter(p -> {

			return gendersChecked.size() != 0 ? gendersChecked.contains(p.getProductInfo().getGender()) : true;
		}).toList();

		// Dùng stream() tạo ra list thì list này không bị sửa đổi được vì thế tạo ra
		// một list khác
		return new ArrayList<>(gendersFilter);

	}

	public List<Product> getAllExists() {
		List<Product> products = productRepository.findAllExists();
		return products;
	}

	public Product getProductById(int productid) {
		Optional<Product> product = productRepository.findById(productid);
		if (product.isPresent()) {
			//addLinkImage(product.get());
			return product.get();
		}
		return null;
	}

	public List<Product> getProductByBrand(Brand brand) {
		List<Product> products = productRepository.findByBrand(brand);
		return products;
	}

	public List<Product> copy(List<Product> old) {
		List<Product> newList = new ArrayList<>();
		for (Product product : old) {
			newList.add(getProductById(product.getProductId()));
		}
		return newList;
	}
	
	public Product getByIdExists(int id) {
		return productRepository.findByIdExists(id);
	}
	
	public void save(Product product) {
		productRepository.save(product);
	}
}





