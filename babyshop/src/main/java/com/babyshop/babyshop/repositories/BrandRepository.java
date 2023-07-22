package com.babyshop.babyshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.babyshop.babyshop.models.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer>{
	@Query(value = "SELECT * FROM brand", nativeQuery = true)
	List<Brand> findAllExists();
	
	@Query(value = "SELECT * FROM brand b where b.brand_id =:brandId", nativeQuery = true)
	Brand findByIdExists(@Param("brandId") int brandId);
}
