package com.babyshop.babyshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babyshop.babyshop.models.Cart;
import com.babyshop.babyshop.repositories.CartRepository;

@Service
public class CartService {
	@Autowired
	CartRepository cartRepository;
	
	public void save(Cart cart) {
		cartRepository.save(cart);
	}
	public Cart getById(int id) {
		return cartRepository.findById(id).get();
	}
}
