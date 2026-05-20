package com.example.demo.dto;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;

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