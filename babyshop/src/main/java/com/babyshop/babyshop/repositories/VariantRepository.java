package com.babyshop.babyshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.babyshop.babyshop.models.Variant;

public interface VariantRepository extends JpaRepository<Variant, Integer>{

}
