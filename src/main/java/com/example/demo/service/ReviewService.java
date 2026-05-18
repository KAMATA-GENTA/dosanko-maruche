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

	/**
	 * 商品ごとのレビュー一覧を取得する。
	 * sortとratingは安全な値に整えてからMapperへ渡す。
	 */
	public List<Review> findByProduct(int productId, String sort, Integer rating) {
		String safeSort = normalizeSort(sort);
		Integer safeRating = normalizeRating(rating);
		return reviewMapper.findByProduct(productId, safeSort, safeRating);
	}

	/**
	 * 並び順の値を安全なものだけに限定する。
	 * 不正な値が来た場合は、新しい順に戻す。
	 */
	public String normalizeSort(String sort) {
		if (sort == null) {
			return "new";
		}

		switch (sort) {
		case "old":
		case "high":
		case "low":
			return sort;
		case "new":
		default:
			return "new";
		}
	}

	/**
	 * 星の数は1〜5だけを有効にする。
	 * それ以外は絞り込みなしにする。
	 */
	public Integer normalizeRating(Integer rating) {
		if (rating == null) {
			return null;
		}

		if (rating >= 1 && rating <= 5) {
			return rating;
		}

		return null;
	}

	/**
	 * 新規レビューをDBに登録する。
	 */
	public void save(Review review) {
		reviewMapper.insert(review);
	}
}
