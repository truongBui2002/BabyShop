package com.babyshop.babyshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.babyshop.babyshop.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	List<Product> findByDiscountLessThan(double discount);
}
