package com.babyshop.babyshop.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.babyshop.babyshop.service.ImageService;

@RestController
public class ImageController {
	
	@Autowired
	public ImageService imageService;
	
	@GetMapping("/img/product/{imgName:.+}")
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
	
	@GetMapping("/img/brand/{brandName:.+}")
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
	@GetMapping("/user/viewprofile/{avatar:.+}")
	public ResponseEntity<byte[]> readDetailFileAvatar(@PathVariable String avatar){
		try {
			byte[] bytes = imageService.readFileContentAvatar(avatar);
			return ResponseEntity
					.ok()
					.contentType(MediaType.IMAGE_JPEG)
					.body(bytes);
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/img/category/{categoryName:.+}")
	public ResponseEntity<byte[]> readDetailFileCategory(@PathVariable String categoryName){
		try {
			byte[] bytes = imageService.readFileContentCategory(categoryName);
			return ResponseEntity
					.ok()
					.contentType(MediaType.IMAGE_JPEG)
					.body(bytes);
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/img/subcategory/{subcategoryName:.+}")
	public ResponseEntity<byte[]> readDetailFileSubcategory(@PathVariable String subcategoryName){
		try {
			byte[] bytes = imageService.readFileContentSubategory(subcategoryName);
			return ResponseEntity
					.ok() 
					.contentType(MediaType.IMAGE_JPEG)
					.body(bytes);
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}
}
