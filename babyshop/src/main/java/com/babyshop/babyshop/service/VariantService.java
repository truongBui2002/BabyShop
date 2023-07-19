package com.babyshop.babyshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babyshop.babyshop.models.Variant;
import com.babyshop.babyshop.repositories.VariantRepository;

@Service
public class VariantService {
	@Autowired
	VariantRepository variantRepository;
	
	public Variant getVariantById(int variantId) {
		Optional<Variant> variant = variantRepository.findById(variantId);
		if(variant.isPresent()) return variant.get();
		return null;
	}
	
}
