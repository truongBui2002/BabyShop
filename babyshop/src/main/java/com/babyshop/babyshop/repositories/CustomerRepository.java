package com.babyshop.babyshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.babyshop.babyshop.models.Customer;
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
