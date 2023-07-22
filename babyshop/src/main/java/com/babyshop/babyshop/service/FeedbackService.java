package com.babyshop.babyshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.babyshop.babyshop.models.Feedback;
import com.babyshop.babyshop.models.Product;
import com.babyshop.babyshop.repositories.FeedbackRepository;
import com.babyshop.babyshop.repositories.ImageRepository;
import com.babyshop.babyshop.util.Status;

@Service
public class FeedbackService {
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	FeedbackRepository feedbackRepository;
	
	public List<Feedback> getFeedBacksActive(Product product){
		List<Feedback> feedbacks = product.getFeedbacks().stream()
									.filter(fb->{
										return fb.getStatus().equals(Status.ACTIVE);
									}).toList();
		return feedbacks;
	}
	public List<Feedback> findAllFeedback(){
		List<Feedback> feedbacks = feedbackRepository.findAllFeedback();
		return feedbacks;
	}
	
	public Feedback getById(int id) {
		return feedbackRepository.findById(id).orElse(null);
	}
	
	public void save(Feedback feedback) {
		feedbackRepository.save(feedback);
	}
	
}
