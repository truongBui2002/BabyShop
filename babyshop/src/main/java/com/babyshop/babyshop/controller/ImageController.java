package com.babyshop.babyshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.babyshop.babyshop.models.ResponseObject;
import com.babyshop.babyshop.service.ImageStorageService;

@RestController
@RequestMapping(path = "/img")
public class ImageController {
	
	@Autowired
	public ImageStorageService storageService;
	
	@GetMapping("/product/{imgName:.+}")
	public ResponseEntity<byte[]> readDetailFile(@PathVariable String imgName){
		try {
			byte[] bytes = storageService.readFileContent(imgName);
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
