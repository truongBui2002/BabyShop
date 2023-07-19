package com.babyshop.babyshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babyshop.babyshop.models.CartItem;
import com.babyshop.babyshop.repositories.CartItemRepository;

@Service
public class CartItemService {
	@Autowired
	CartItemRepository cartItemRepository;
	public void save(CartItem cartItem) {
		cartItemRepository.save(cartItem);
	}
	public void deleteById(int id) {
		cartItemRepository.deleteById(id);
	}
}
