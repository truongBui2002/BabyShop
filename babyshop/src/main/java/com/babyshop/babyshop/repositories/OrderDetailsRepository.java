package com.babyshop.babyshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.babyshop.babyshop.models.OrderDetails;
import com.babyshop.babyshop.models.Product;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer>{
List<OrderDetails> findByProduct(Product product);
}
 