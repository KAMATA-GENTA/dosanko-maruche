package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.Review;

@Mapper
public interface ReviewMapper {

	Double findAverageRating(@Param("productId") int productId);

	List<Review> findByProduct(@Param("productId") int productId);

	int insert(Review review);
}
