package com.babyshop.babyshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.babyshop.babyshop.models.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Integer>{
	public Categories findByProductId(int id);
	public List<Categories> findByCategoryName(String categoryName);
	
}
