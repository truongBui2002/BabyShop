package com.babyshop.babyshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.babyshop.babyshop.models.Location;

public interface LocationRepository extends JpaRepository<Location, Integer>{
}
