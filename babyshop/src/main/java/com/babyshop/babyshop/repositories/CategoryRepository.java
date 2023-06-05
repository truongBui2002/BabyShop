package com.babyshop.babyshop.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.babyshop.babyshop.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
}
