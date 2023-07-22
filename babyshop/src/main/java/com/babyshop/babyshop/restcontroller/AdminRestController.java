package com.babyshop.babyshop.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.babyshop.babyshop.models.Brand;
import com.babyshop.babyshop.models.Customer;
import com.babyshop.babyshop.models.Feedback;
import com.babyshop.babyshop.models.OrderDetails;
import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.models.User;
import com.babyshop.babyshop.models.Variant;
import com.babyshop.babyshop.service.BrandService;
import com.babyshop.babyshop.service.FeedbackService;
import com.babyshop.babyshop.service.OrderDetailsService;
import com.babyshop.babyshop.service.ProductService;
import com.babyshop.babyshop.service.UserService;
import com.babyshop.babyshop.service.VariantService;
import com.babyshop.babyshop.util.Status;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RestController
public class AdminRestController {
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
	@Autowired
	VariantService variantService;
	
	@PersistenceContext
	private EntityManager entityManager;

	@PutMapping("/manager/staff/feedback-management/change-status")
	public String changeStatusFeedback(@RequestBody String data) {
		int feedbackId = Integer.parseInt(data);
		Feedback feedback = feedbackService.getById(feedbackId);
		if (feedback != null) {
			if (feedback.getStatus().equals(Status.ACTIVE)) {
				feedback.setStatus(Status.INACTIVE);
				feedbackService.save(feedback);
				return Status.INACTIVE;
			} else {
				feedback.setStatus(Status.ACTIVE);
				feedbackService.save(feedback);
				return Status.ACTIVE;
			}
		}
		return "Not Found";
	}

	@PutMapping("/manager/admin/list-user/change-status")
	public String changeStatusUser(@RequestBody String data) {
		System.out.println(data);
		int userId = Integer.parseInt(data);
		User user = userService.getUserByIdExists(userId);
		if (user != null) {
			if (user.getStatus().equals(Status.LOCK)) {
				user.setStatus(Status.UNLOCK);
				userService.save(user);
				return Status.UNLOCK;
			} else {
				user.setStatus(Status.LOCK);
				userService.save(user);
				return Status.LOCK;
			}
		}
		return "Not Found";
	}

	@PutMapping("/manager/staff/order-list/change-status/ship")
	public String changeStatusOrderDetailsShip(@RequestBody String data) {
		int oddId = Integer.parseInt(data);
		OrderDetails odDetails = orderDetailsService.getById(oddId);
		if (odDetails != null) {
			if (odDetails.getStatus().equals(Status.WAIT)) {
				odDetails.setStatus(Status.SHIP);
				orderDetailsService.save(odDetails);
				return Status.SHIP;
			}
		}
		return "shipping";
	}

	@PutMapping("/manager/staff/order-list/change-status/complete")
	public String changeStatusOrderDetailsComplete(@RequestBody String data) {
		int oddId = Integer.parseInt(data);
		OrderDetails odDetails = orderDetailsService.getById(oddId);
		if (odDetails != null) {
			if (odDetails.getStatus().equals(Status.SHIP)) {
				odDetails.setStatus(Status.COMPLETED);
				orderDetailsService.save(odDetails);
				return Status.COMPLETED;
			}
		}
		return "complete";
	}

	@PutMapping("/manager/staff/order-list/change-status/cancel")
	public String changeStatusOrderDetailsCancel(@RequestBody String data) {
		int oddId = Integer.parseInt(data);
		OrderDetails odDetails = orderDetailsService.getById(oddId);
		if (odDetails != null) {
			if (odDetails.getStatus().equals(Status.WAIT)) {
				Variant variant = odDetails.getVariant();
				// hủy đơn thì hoàn lại số lượng sản phẩm trong kho
				variant.setQuantity(variant.getQuantity() + odDetails.getQuantity());
				variantService.save(variant);
				odDetails.setStatus(Status.CANCELLED);
				orderDetailsService.save(odDetails);
				return Status.CANCELLED;
			}
		}
		return "cancel";
	}

	@PutMapping("/manager/staff/list-product/change-status")
	public String changeStatusProduct(@RequestBody String data) {
		int productId = Integer.parseInt(data);
		Product product = productService.getByIdExists(productId);
		if (product != null) {
			if (product.getStatus().equals(Status.UNLOCK)) {
				product.setStatus(Status.LOCK);
				productService.save(product);
				return Status.LOCK;
			} else {
				String query = "SELECT b.* FROM product p JOIN brand b ON p.brand_id = b.brand_id WHERE p.product_id = :productId";
				Brand brand = (Brand) entityManager.createNativeQuery(query, Brand.class)
						.setParameter("productId", product.getProductId()).getSingleResult();
				if(!brand.getStatus().equals(Status.LOCK)) {
					product.setStatus(Status.UNLOCK);
					productService.save(product);
					return Status.UNLOCK;
				}
				return Status.LOCK;
			}
		}
		return "product status";
	}

	@PutMapping("/manager/staff/list-brand/change-status")
	public String changeStatusBrand(@RequestBody String data) {
		int brandId = Integer.parseInt(data);
		Brand brand = brandService.getBranByIdExists(brandId);
		List<Product> products = brand.getProducts();
		if (brand.getStatus().equals(Status.UNLOCK)) {
			brand.setStatus(Status.LOCK);
			if (products != null) {
				for (Product product : products) {
					product.setStatus(Status.LOCK);
				}
			}
			brandService.save(brand);
			return Status.LOCK;
		}else {
			brand.setStatus(Status.UNLOCK);
			String query = "SELECT p.* FROM product p WHERE p.brand_id = :brandId";
			products = entityManager.createNativeQuery(query, Product.class)
			    .setParameter("brandId", brand.getBrandId())
			    .getResultList();
			if (products != null) {
				for (Product product : products) {
					product.setStatus(Status.UNLOCK);
				}
			}
			brandService.save(brand);
			return Status.UNLOCK;
		}
	}

}
