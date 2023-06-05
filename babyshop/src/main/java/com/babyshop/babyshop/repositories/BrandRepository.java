package com.babyshop.babyshop.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.babyshop.babyshop.models.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer>{
	
}
