package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.mapper.CartMapper;

@Service
public class CartService {

	private final CartMapper cartMapper;

	public CartService(CartMapper cartMapper) {
		this.cartMapper = cartMapper;
	}

	public void addToCart(int userId, int productId, int quantity) {
		cartMapper.insert(userId, productId, quantity);
	}
}