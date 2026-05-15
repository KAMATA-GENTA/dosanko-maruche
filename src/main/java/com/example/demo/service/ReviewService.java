package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Review;
import com.example.demo.mapper.ReviewMapper;

@Service
public class ReviewService {

	private final ReviewMapper reviewMapper;

	public ReviewService(ReviewMapper reviewMapper) {
		this.reviewMapper = reviewMapper;
	}

	public Double getAverageRating(int productId) {
		return reviewMapper.findAverageRating(productId);
	}

	public List<Review> findByProduct(int productId) {
		return reviewMapper.findByProduct(productId);
	}

	public void save(Review review) {
		reviewMapper.insert(review);
	}
}
