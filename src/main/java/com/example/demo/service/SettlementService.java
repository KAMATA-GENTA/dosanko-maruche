package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;

@Service
public class SettlementService {

	private static final int BASE_SHIPPING_FEE = 800;

	private final ProductService productService;

	public SettlementService(ProductService productService) {
		this.productService = productService;
	}

	public int calcSubtotal(List<CartItem> cartItems) {
		int subtotal = 0;

		for (CartItem cartItem : cartItems) {
			Product product = productService.findById(cartItem.getProductId());
			subtotal += product.getPrice() * cartItem.getQuantity();
		}

		return subtotal;
	}

	public int calcShippingFee(boolean isHit) {
		return isHit ? 0 : BASE_SHIPPING_FEE;
	}

	public int calcTotalPrice(int subtotal, int shippingFee) {
		return subtotal + shippingFee;
	}

	public int getBaseShippingFee() {
		return BASE_SHIPPING_FEE;
	}
}