package com.babyshop.babyshop.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.babyshop.babyshop.service.ImageService;

@RestController
@RequestMapping(path = "/img")
public class ImageController {
	
	@Autowired
	public ImageService imageService;
	
	@GetMapping("/product/{imgName:.+}")
	public ResponseEntity<byte[]> readDetailFileProduct(@PathVariable String imgName){
		try {
			byte[] bytes = imageService.readFileContentProduct(imgName);
			return ResponseEntity
					.ok()
					.contentType(MediaType.IMAGE_JPEG)
					.body(bytes);
		} catch (Exception e) { 
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/brand/{brandName:.+}")
	public ResponseEntity<byte[]> readDetailFileBrand(@PathVariable String brandName){
		try {
			byte[] bytes = imageService.readFileContentBrand(brandName);
			return ResponseEntity
					.ok()
					.contentType(MediaType.IMAGE_JPEG)
					.body(bytes);
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}
}
