package com.babyshop.babyshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.babyshop.babyshop.models.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{

}
