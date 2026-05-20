package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.CartItem;

@Mapper
public interface CartMapper {

	List<CartItem> findByUserId(
			@Param("userId") Integer userId);

	void deleteByUserId(
			@Param("userId") Integer userId);

	void insert(
			@Param("userId") int userId,
			@Param("productId") int productId,
			@Param("quantity") int quantity);

	void updateQuantity(
			@Param("cartId") int cartId,
			@Param("quantity") int quantity);

	void delete(
			@Param("cartId") int cartId);
}