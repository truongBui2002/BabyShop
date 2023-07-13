package com.babyshop.babyshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.babyshop.babyshop.models.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
