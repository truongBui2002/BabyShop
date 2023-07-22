package com.babyshop.babyshop.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.babyshop.babyshop.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);

	User findByPhoneNumber(String phoneNumber);

	@Transactional
	Optional<User> findById(int userId);

	@Query(value = "SELECT * FROM user", nativeQuery = true)
	List<User> findAllUsers();
	
	@Query(value = "SELECT * FROM user u where u.user_id =:uid", nativeQuery = true)
	User findByUserExsist(@Param("uid") int uid);
}
