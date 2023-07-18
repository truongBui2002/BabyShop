package com.babyshop.babyshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.babyshop.babyshop.models.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{
	@Modifying
	@Query("DELETE FROM CartItem ci WHERE ci.cartItemId = :cartItemId")
	void deleteById(@Param("cartItemId") int id);
	 
}
