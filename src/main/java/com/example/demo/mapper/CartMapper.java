package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.CartItem;

@Mapper
public interface CartMapper {

	void insert(
			@Param("userId") int userId,
			@Param("productId") int productId,
			@Param("quantity") int quantity);

	List<CartItem> findByUserId(int userId);

	CartItem findByUserIdAndProductId(
			@Param("userId") int userId,
			@Param("productId") int productId);

	void updateQuantity(
			@Param("cartId") int cartId,
			@Param("quantity") int quantity);

	void delete(int cartId);

	void deleteByUserId(int userId);
}