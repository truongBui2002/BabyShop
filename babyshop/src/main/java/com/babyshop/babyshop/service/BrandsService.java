package com.babyshop.babyshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babyshop.babyshop.models.Brands;
import com.babyshop.babyshop.repositories.BrandsRepository;

@Service
public class BrandsService {
	
	@Autowired 
	BrandsRepository brandsRepository;
	
	//lấy ra toàn bộ thương hiệu
	public List<Brands> getAll(){
		
		return brandsRepository.findAll();
	}
	
	
	
}
