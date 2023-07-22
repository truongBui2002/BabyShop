package com.babyshop.babyshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.babyshop.babyshop.models.OrderDetails;
import com.babyshop.babyshop.models.Product;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
	List<OrderDetails> findByProduct(Product product);
	@Query(value = "SELECT * FROM order_details", nativeQuery = true)
	List<OrderDetails> findAllOrderDetails();
}
