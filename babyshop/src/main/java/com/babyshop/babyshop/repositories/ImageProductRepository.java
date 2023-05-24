package com.babyshop.babyshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.babyshop.babyshop.models.ImageProduct;

public interface ImageProductRepository extends JpaRepository<ImageProduct, Integer>{
	@Query("SELECT img FROM ImageProduct img WHERE img.productId = :productId")
	public List<ImageProduct> getByProductId(@Param(value = "productId") int productId);
}
