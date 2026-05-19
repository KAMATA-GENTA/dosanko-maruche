package com.example.demo.entity;

import lombok.Data;

@Data
public class CartDisplayItem {

	private CartItem cartItem;
	private Product product;
	private Integer subtotal;

	public CartDisplayItem(CartItem cartItem, Product product) {
		this.cartItem = cartItem;
		this.product = product;
		this.subtotal = product.getPrice() * cartItem.getQuantity();
	}
}