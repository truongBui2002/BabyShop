package com.babyshop.babyshop.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.babyshop.babyshop.models.Brand;
import com.babyshop.babyshop.models.Category;
import com.babyshop.babyshop.models.Feedback;
import com.babyshop.babyshop.models.Image;
import com.babyshop.babyshop.models.OrderDetails;
import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.models.ProductInfo;
import com.babyshop.babyshop.models.Role;
import com.babyshop.babyshop.models.Subcategory;
import com.babyshop.babyshop.models.User;
import com.babyshop.babyshop.models.Variant;
import com.babyshop.babyshop.service.BrandService;
import com.babyshop.babyshop.service.CategoryService;
import com.babyshop.babyshop.service.FeedbackService;
import com.babyshop.babyshop.service.ImageService;
import com.babyshop.babyshop.service.OrderDetailsService;
import com.babyshop.babyshop.service.ProductService;
import com.babyshop.babyshop.service.RoleService;
import com.babyshop.babyshop.service.SubcategoryService;
import com.babyshop.babyshop.service.UserService;
import com.babyshop.babyshop.service.VariantService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpSession;
import lombok.experimental.var;

@Controller
public class AdminController {
	@Autowired
	FeedbackService feedbackService;

	@Autowired
	UserService userService;

	@Autowired
	OrderDetailsService orderDetailsService;

	@Autowired
	ProductService productService;

	@Autowired
	BrandService brandService;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	ImageService imageService;

	@Autowired
	HttpSession session;

	@Autowired
	RoleService roleService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	SubcategoryService subcategoryService;

	@Autowired
	VariantService variantService;

	@GetMapping("/manager/dashboard")
	public String admin() {

		return "admin/dashboard";
	}

	@GetMapping("/manager/staff/order-list")
	public String orderList(ModelMap modelMap) {
		List<OrderDetails> orderDetails = orderDetailsService.getAll();
		modelMap.addAttribute("orderDetails", orderDetails);
		return "admin/order-list";
	}

	@GetMapping("/manager/staff/feedback-management")
	public String feedback(ModelMap modelMap) {
		List<Feedback> feedbacks = feedbackService.findAllFeedback();
		modelMap.addAttribute("feedbacks", feedbacks);
		return "admin/feedback-management";
	}

	@GetMapping("/manager/admin/list-user")
	public String listUser(ModelMap modelMap) {
		List<User> users = userService.findAllUsers();
		modelMap.addAttribute("users", users);
		return "admin/list-user";
	}

	@GetMapping("/manager/staff/list-product")
	public String listProduct(ModelMap modelMap) {
		List<Product> products = productService.getAllExists();
		for (Product product : products) {
			String query = "SELECT b.* FROM product p JOIN brand b ON p.brand_id = b.brand_id WHERE p.product_id = :productId";
			Brand brand = (Brand) entityManager.createNativeQuery(query, Brand.class)
					.setParameter("productId", product.getProductId()).getSingleResult();
			product.setBrandExists(brand);
		}
		List<Brand> brands = brandService.getAllExists();
		List<Category> categories = categoryService.getAll();
		List<Subcategory> subcategories = subcategoryService.getAll();

		modelMap.addAttribute("products", products);
		modelMap.addAttribute("brands", brands);
		modelMap.addAttribute("categories", categories);
		modelMap.addAttribute("subcategories", subcategories);
		return "admin/list-product";
	}

	@GetMapping("/manager/staff/list-brand")
	public String listBrand(ModelMap modelMap) {
		List<Brand> brands = brandService.getAllExists();
		modelMap.addAttribute("brands", brands);
		return "admin/list-brand";
	}

	@PostMapping("/manager/staff/brand/add-or-update")
	public String addBrand(@RequestParam(name = "brand-id", defaultValue = "") String brnId,
			@RequestParam(name = "image-brand", defaultValue = "") MultipartFile fileImage,
			@RequestParam(name = "brandName", defaultValue = "") String brandName,
			@RequestParam(name = "brandDescription", defaultValue = "") String brandDescription) {
		System.out.println("brandId: " + brnId);
		System.out.println("file: " + fileImage.isEmpty());
		System.out.println("brandName: " + brandName);
		System.out.println("brandDescription: " + brandDescription);
		// Tạo mới một brand
		if (brnId.equals("")) {
			Brand brand = new Brand();
			if (!(fileImage.isEmpty() && brandName.equals("") && brandDescription.equals(""))) {
				brand.setName(brandName);
				brand.setDescription(brandDescription);
				String fileName = imageService.saveImageBrand(fileImage);
				Image image = new Image();
				image.setName(fileName);
				brand.setImage(image);
				brandService.save(brand);
			}
		} else {
			// Update một brand
			int brandId = Integer.parseInt(brnId);
			Brand brand = brandService.getBranByIdExists(brandId);
			if (!(brandName.equals("") && brandDescription.equals(""))) {
				brand.setName(brandName);
				brand.setDescription(brandDescription);
				if (!fileImage.isEmpty()) {
					String fileName = imageService.saveImageBrand(fileImage);
					Image image = new Image();
					image.setName(fileName);
					brand.setImage(image);
				}
				brandService.save(brand);
			}
		}

		System.out.println("brandId: " + brnId);
		System.out.println("file: " + fileImage.isEmpty());
		System.out.println("brandName: " + brandName);
		System.out.println("brandDescription: " + brandDescription);
		return "redirect:/manager/staff/list-brand";
	}

	@GetMapping("/manager/admin/add-user")
	public String addUserView() {

		return "admin/add-user";
	}

	@PostMapping("/manager/admin/add-user")
	public String addUser(@RequestParam(name = "role") String ro,
			@RequestParam(name = "file-image", defaultValue = "") MultipartFile fileImage,
			@RequestParam(name = "fullName", defaultValue = "") String fullName,
			@RequestParam(name = "dob", defaultValue = "") String date,
			@RequestParam(name = "phoneNumber", defaultValue = "") String phoneNumber,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			@RequestParam(name = "re-pass", defaultValue = "") String re_pass) {
		System.out.println(ro);
		System.out.println(fileImage.getOriginalFilename());
		System.out.println(fullName);
		System.out.println(date);
		System.out.println(phoneNumber);
		System.out.println(email);
		System.out.println(re_pass);
		if (!(phoneNumber.equals("") && email.equals("") && password.equals("") && re_pass.equals(""))) {
			if (password.equals(re_pass)
					&& !(userService.userIsExists(phoneNumber) && userService.userIsExists(email))) {
				User user = new User();
				Role role = roleService.getByName(ro);
				if (role == null) {
					role = new Role(ro);
					role.getUsers().add(user);

				}
				user.setRoles(Arrays.asList(role));
				if (!fileImage.isEmpty()) {
					String fileName = imageService.saveAvatar(fileImage);
					Image image = new Image(fileName);
					user.setImage(image);
				}
				if (!date.equals("")) {
					LocalDate localDate = LocalDate.parse(date);
					user.setDob(localDate);
				}
				user.setFullName(fullName);
				user.setPhoneNumber(phoneNumber);
				user.setEmail(email);
				user.setPassword(password);
				userService.saveUserEncoder(user);
				return "redirect:/manager/admin/list-user?success";
			}
		}

		return "redirect:/manager/admin/list-user?error";
	}

	@GetMapping("/manager/user-profile")
	public String userProfile() {
		User user = (User) session.getAttribute("manager");
		user = userService.getUserById(user.getUserId());
		session.setAttribute("manager", user);
		return "admin/user-profile";
	}

	@PostMapping("/manager/staff/product/add-or-update")
	public String addOrUpdateProduct(@RequestParam(name = "product-id", defaultValue = "") String productId,
			@RequestParam(name = "subcategory", defaultValue = "") String subcategoryId,
			@RequestParam(name = "brand", defaultValue = "") String brandId,
			@RequestParam(name = "productName", defaultValue = "") String productName,
			@RequestParam(name = "price", defaultValue = "") String price,
			@RequestParam(name = "description", defaultValue = "") String description,
			@RequestParam(name = "specification", defaultValue = "") String specification,
			@RequestParam(name = "discount", defaultValue = "") String discount,
			@RequestParam(name = "checkbox-size", defaultValue = "") List<String> size,
			@RequestParam(name = "age", defaultValue = "") String age,
			@RequestParam(name = "gender", defaultValue = "") String gender,
			@RequestParam(name = "color", defaultValue = "") String color,
			@RequestParam(name = "file-image") List<MultipartFile> imagesFile) {

		System.out.println("productId: " + productId);
		System.out.println("subcategoryId: " + subcategoryId);
		System.out.println("brand: " + brandId);
		System.out.println("productName: " + productName);
		System.out.println("price: " + price);
		System.out.println("description: " + description);
		System.out.println("specification: " + specification);
		System.out.println("discount: " + discount);
		System.out.println("size" + size);
		System.out.println("age: " + age);
		System.out.println("gender: " + gender);
		System.out.println("color: " + color);
		System.out.println("imagesFile : " + imagesFile.get(0).isEmpty());
		System.out.println("END");

		// Add new product
		if (productId.equals("")) {
			Product product = new Product();
			product.setName(productName);
			product.setPrice(Double.parseDouble(price));
			product.setDescription(description);
			product.setSpecification(specification);
			product.setDiscount(Double.parseDouble(discount) / 100);

			Subcategory subcategory = subcategoryService.getById(Integer.parseInt(subcategoryId));
			Brand brand = brandService.getBranByIdExists(Integer.parseInt(brandId));

			ProductInfo pf = new ProductInfo();
			pf.setAgeRange(age);
			pf.setGender(gender);
			pf.setColor(color);
			pf.setProduct(product);
			List<Image> images = new ArrayList<>();
			for (MultipartFile file : imagesFile) {
				String fileName = imageService.saveImageProduct(file);
				Image image = new Image(fileName);
				images.add(image);
			}
			product.setSubcategory(subcategory);
			product.setBrand(brand);
			product.setProductInfo(pf);
			product.setImages(images);
			productService.save(product);
			for (String variantName : size) {
				Variant variant = new Variant(variantName);
				variant.setQuantity(0);
				variant.setProduct(product);
				variantService.save(variant);

			}

		} else {
			int id = Integer.parseInt(productId);
			Product product = productService.getByIdExists(id);
			product.setName(productName);
			product.setPrice(Double.parseDouble(price));
			product.setDescription(description);
			product.setSpecification(specification);
			product.setDiscount(Double.parseDouble(discount) / 100);
			if (Character.isDigit(subcategoryId.charAt(0))) {
				Subcategory subcategory = subcategoryService.getById(Integer.parseInt(subcategoryId));
				product.setSubcategory(subcategory);
			}
			if (Character.isDigit(brandId.charAt(0))) {
				Brand brand = brandService.getBranByIdExists(Integer.parseInt(brandId));
				product.setBrand(brand);
			}

			ProductInfo pf = product.getProductInfo();
			pf.setAgeRange(age);
			pf.setGender(gender);
			pf.setColor(color);
			product.setProductInfo(pf);
			if (!imagesFile.get(0).getOriginalFilename().isEmpty()) {
				product.getImages().clear();
				List<Image> images = product.getImages();
				for (MultipartFile file : imagesFile) {
					String fileName = imageService.saveImageProduct(file);
					Image image = new Image(fileName);
					images.add(image);
				}
			}

			productService.save(product);
			List<String> newSize = size.stream().filter(z -> {
				for (Variant variant : product.getVariants()) {
					if (variant.getName().equals(z))
						return false;
				}
				return true;
			}).toList();
			for (String variantName : newSize) {
				Variant variant = new Variant(variantName);
				variant.setQuantity(0);
				variant.setProduct(product);
				variantService.save(variant);

			}
		}

		return "redirect:/manager/staff/list-product";

	}

	@PostMapping("/manager/staff/product/edit-size")
	public String editSize(@RequestParam(name = "product-id") String productId,
			@RequestParam(name = "size", defaultValue = "") List<Integer> sizes,
			@RequestParam(name = "variant", defaultValue = "") List<Integer> variantsId) {
		Product product = productService.getByIdExists(Integer.parseInt(productId));

		List<Variant> variants = product.getVariants();
		for (int i = 0; i < variants.size(); i++) {
			Variant variant = variants.get(i);
			variant.setQuantity(sizes.get(i));
			variantService.save(variant);
		}

//		for (Integer variantId : variantsId) {
//			System.out.println("variantId: " + variantId);
//			variantService.deleteById(variantId);
//		}
		return "redirect:/manager/staff/list-product";
	}
}
