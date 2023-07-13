package com.babyshop.babyshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babyshop.babyshop.models.OrderDetails;
import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.repositories.OrderDetailsRepository;
import com.babyshop.babyshop.util.Status;

@Service
public class OrderDetailsService {
	@Autowired
	OrderDetailsRepository orderDetailsRepository;
	
	public int getTotalProductSold(Product product) {
		List<OrderDetails> orderDetails = orderDetailsRepository.findByProduct(product);
		int totalSold = orderDetails.stream()
				.filter(od -> od.getStatus().equals(Status.COMPLETED))
				.mapToInt(od -> od.getQuantity()).sum();
		return totalSold;
	}
}
