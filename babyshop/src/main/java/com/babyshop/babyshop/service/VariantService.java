package com.babyshop.babyshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babyshop.babyshop.models.Variant;
import com.babyshop.babyshop.repositories.VariantRepository;

@Service
public class VariantService {
	@Autowired
	VariantRepository variantRepository;
	
	public Variant getVariantById(int variantId) {
		return variantRepository.findById(variantId).orElse(null);
	}
	
	public void save (Variant variant) {
		variantRepository.save(variant);
	}
	public void deleteById(int id) {
		System.out.println("DA TOI XOA");
		variantRepository.deleteById(id);
		System.out.println("DA XOA");
	}
}
