package com.babyshop.babyshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.babyshop.babyshop.models.Image;

public interface ImageRepository extends JpaRepository<Image, Integer>{

}
