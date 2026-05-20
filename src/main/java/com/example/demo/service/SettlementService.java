package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CartDisplayItem;

@Service
public class SettlementService {

	private static final int BASE_SHIPPING_FEE = 800;

	public int calcSubTotal(List<CartDisplayItem> cartItems) {
		int subtotal = 0;

		for (CartDisplayItem item : cartItems) {
			subtotal += item.getSubtotal();
		}

		return subtotal;
	}

	public int calcShippingFee(boolean isFreeShipping) {
		return isFreeShipping ? 0 : BASE_SHIPPING_FEE;
	}

	public int calcTotalPrice(List<CartDisplayItem> cartItems, boolean isFreeShipping) {
		int subtotal = calcSubTotal(cartItems);
		int shippingFee = calcShippingFee(isFreeShipping);

		return subtotal + shippingFee;
	}
}