package com.babyshop.babyshop.service;

import java.util.Collections;
import java.util.Comparator;
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
	public void save(OrderDetails orderDetails) {
		orderDetailsRepository.save(orderDetails);
	}
	
	public void sortByTime(List<OrderDetails> odDetails) {
		Collections.sort(odDetails, new Comparator<OrderDetails>() {
			@Override
			public int compare(OrderDetails o1, OrderDetails o2) {
				return o1.getCreatedAt().compareTo(o2.getCreatedAt());
			}
		});
	}
}
