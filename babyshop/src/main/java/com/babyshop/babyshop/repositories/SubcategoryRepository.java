package com.babyshop.babyshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.babyshop.babyshop.models.Subcategory;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer>{
	Subcategory findByName(String name);
}
