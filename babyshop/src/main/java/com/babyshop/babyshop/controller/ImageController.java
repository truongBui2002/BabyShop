package com.babyshop.babyshop.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.babyshop.babyshop.service.ImageStorageService;

@RestController
@RequestMapping(path = "/img")
public class ImageController {
	
	@Autowired
	public ImageStorageService storageService;
	
	@GetMapping("/product/{imgName:.+}")
	public ResponseEntity<byte[]> readDetailFileProduct(@PathVariable String imgName){
		try {
			byte[] bytes = storageService.readFileContentProduct(imgName);
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
			byte[] bytes = storageService.readFileContentBrand(brandName);
			return ResponseEntity
					.ok()
					.contentType(MediaType.IMAGE_JPEG)
					.body(bytes);
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
		
	}
	@GetMapping("")
	public String getURL() {
//		List<String> uris = storageService.getURIImage(url);
//		System.out.print("URIS la: " + uris.get(0));
		return "";
	}
}
