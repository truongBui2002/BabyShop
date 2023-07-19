package com.babyshop.babyshop.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.babyshop.babyshop.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmail(String email);
	User findByPhoneNumber(String phoneNumber);
	@Transactional
	Optional<User> findById(int userId);
}
