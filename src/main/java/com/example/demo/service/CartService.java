package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CartItem;
import com.example.demo.mapper.CartMapper;

@Service
public class CartService {

	private final CartMapper cartMapper;

	public CartService(CartMapper cartMapper) {
		this.cartMapper = cartMapper;
	}

	public void addToCart(int userId, int productId, int quantity) {

		CartItem existingItem = cartMapper.findByUserIdAndProductId(userId, productId);

		if (existingItem == null) {
			cartMapper.insert(userId, productId, quantity);
			return;
		}

		int newQuantity = existingItem.getQuantity() + quantity;

		cartMapper.updateQuantity(existingItem.getId(), newQuantity);
	}

	public void changeQuantity(int cartId, int quantity) {

		if (quantity <= 0) {
			cartMapper.delete(cartId);
			return;
		}

		cartMapper.updateQuantity(cartId, quantity);
	}
}