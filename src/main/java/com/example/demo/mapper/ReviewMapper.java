package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Review;

@Mapper
public interface ReviewMapper {

	/** 全商品を取得する */
	List<Review> findAll();

}
