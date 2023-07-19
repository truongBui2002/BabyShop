package com.babyshop.babyshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babyshop.babyshop.models.Location;
import com.babyshop.babyshop.repositories.LocationRepository;

@Service
public class LocationService {
	@Autowired
	LocationRepository locationRepository;
	public Location getById(int id) {
		return locationRepository.findById(id).orElse(null);
	}
	
}
