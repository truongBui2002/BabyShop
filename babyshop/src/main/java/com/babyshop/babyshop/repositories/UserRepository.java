package com.babyshop.babyshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.babyshop.babyshop.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmail(String email);
	User findByPhoneNumber(String phoneNumber);
}
