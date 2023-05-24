package com.babyshop.babyshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babyshop.babyshop.models.Image;
import com.babyshop.babyshop.repositories.ImageRepository;

@Service
public class ImageService {
	@Autowired
	ImageRepository imageRepository;

	public Image getImageById(int Id) {
		Optional<Image> img = imageRepository.findById(Integer.valueOf(Id));
		if (img.isPresent())
			return img.get();
		return null;
	}
}
