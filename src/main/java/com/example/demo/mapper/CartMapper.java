package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.CartItem;

@Mapper
public interface CartMapper {

	List<CartItem> findByUserId(Integer userId);

	void deleteByUserId(Integer userId);
}