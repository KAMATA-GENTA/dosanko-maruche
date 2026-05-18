package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.CartItem;

@Mapper
public interface CartMapper {

	List<CartItem> findByUserId(Integer userId);

	void deleteByUserId(Integer userId);
	// カート追加
	void insert(@Param("userId") int userId,
			@Param("productId") int productId,
			@Param("quantity") int quantity);



	// 数量更新
	void updateQuantity(@Param("cartId") int cartId,
			@Param("quantity") int quantity);

	// 削除
	void delete(int cartId);
}