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

	/**
	 * 商品ごとの平均評価を取得する。
	 * 小数第2位以下はReviewMapper.xml側のSQLで切り捨てる。
	 */
	public Double getAverageRating(int productId) {
		Double averageRating = reviewMapper.findAverageRating(productId);
		return averageRating == null ? 0.0 : averageRating;
	}

	public List<Review> findByProduct(int productId) {
		return reviewMapper.findByProduct(productId);
	}

	/**
	 * 新規レビューをDBに登録する。
	 */
	public void save(Review review) {
		reviewMapper.insert(review);
	}
}
