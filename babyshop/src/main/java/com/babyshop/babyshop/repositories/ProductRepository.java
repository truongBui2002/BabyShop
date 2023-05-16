package com.babyshop.babyshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.babyshop.babyshop.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
