package com.babyshop.babyshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babyshop.babyshop.models.Customer;
import com.babyshop.babyshop.repositories.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	public void save(Customer customer) {
		customerRepository.save(customer);
	}
}
