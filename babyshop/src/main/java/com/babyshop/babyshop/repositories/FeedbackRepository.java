package com.babyshop.babyshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.babyshop.babyshop.models.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{
	@Query(value = "SELECT * FROM feedback", nativeQuery = true)
	List<Feedback> findAllFeedback();
}
