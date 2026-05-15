package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Review;

@Mapper
public interface ReviewMapper {

	Double findAverageRating(int productId);

	List<Review> findByProduct(int productId);

	void insert(Review review);
}
