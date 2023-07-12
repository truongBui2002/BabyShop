package com.babyshop.babyshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.babyshop.babyshop.controller.ImageController;
import com.babyshop.babyshop.models.Feedback;
import com.babyshop.babyshop.models.Image;
import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.repositories.ImageRepository;
import com.babyshop.babyshop.util.Status;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class FeedbackService {
	@Autowired
	ImageRepository imageRepository;
	
	@PersistenceContext // Tự động tạo ra EntityManager và cấu hình vào Spring
	EntityManager entityManager;
	public void addLinkImage(Feedback feedback) {
		List<Image> images = new ArrayList<>();
		for (Image image : feedback.getImages()) {
			// Loại bỏ những image đã được JPA tạo ra
			entityManager.detach(image);
			Image img = imageRepository.findById(image.getImageId()).get();
			String imageName = MvcUriComponentsBuilder
					.fromMethodName(ImageController.class, "readDetailFileFeedback", img.getName()).build().toUri()
					.toString();

			image.setName(imageName);
			images.add(image);
		}
		feedback.setImages(images);
	}
	
	public List<Feedback> getFeedBacksActive(Product product){
		List<Feedback> feedbacks = product.getFeedbacks().stream()
									.filter(fb->{
										return fb.getStatus().equals(Status.ACTIVE);
									}).toList();
		for (Feedback feedback : feedbacks) {
			addLinkImage(feedback);
		}
		return feedbacks;
	}
}
