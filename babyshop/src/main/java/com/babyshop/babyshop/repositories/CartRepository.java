package com.babyshop.babyshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.babyshop.babyshop.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

}
