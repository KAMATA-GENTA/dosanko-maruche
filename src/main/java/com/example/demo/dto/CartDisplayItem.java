package com.example.demo.dto;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;

public class CartDisplayItem {

	private final CartItem cartItem;
	private final Product product;

	public CartDisplayItem(CartItem cartItem, Product product) {
		this.cartItem = cartItem;
		this.product = product;
	}

	public int getCartId() {
		return cartItem.getId();
	}

	public int getProductId() {
		return cartItem.getProductId();
	}

	public String getProductName() {
		return product.getName();
	}

	public int getPrice() {
		return product.getPrice();
	}

	public int getQuantity() {
		return cartItem.getQuantity();
	}

	public int getSubtotal() {
		return product.getPrice() * cartItem.getQuantity();
	}

	public String getImageUrl() {
		return product.getImageUrl();
	}
}