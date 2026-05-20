package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.dto.CartDisplayItem;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;
import com.example.demo.mapper.CartMapper;
import com.example.demo.service.ProductService;
import com.example.demo.service.SettlementService;

@Controller
public class CartController {

	private final CartMapper cartMapper;
	private final ProductService productService;
	private final SettlementService settlementService;

	public CartController(CartMapper cartMapper, ProductService productService, SettlementService settlementService) {
		this.cartMapper = cartMapper;
		this.productService = productService;
		this.settlementService = settlementService;
	}

	@GetMapping("/cart")
	public String showCart(
			Model model,
			@SessionAttribute(value = "userId", required = false) Integer userId) {

		if (userId == null) {
			return "redirect:/login";
		}

		List<CartItem> cartItems = cartMapper.findByUserId(userId);

		List<CartDisplayItem> displayItems = new ArrayList<>();

		for (CartItem cartItem : cartItems) {
			Product product = productService.findById(cartItem.getProductId());
			displayItems.add(new CartDisplayItem(cartItem, product));
		}

		int subtotal = settlementService.calcSubtotal(cartItems);
		int shippingFee = settlementService.getBaseShippingFee();
		int totalPrice = settlementService.calcTotalPrice(subtotal, shippingFee);

		model.addAttribute("cartItems", displayItems);
		model.addAttribute("subtotal", subtotal);
		model.addAttribute("shippingFee", shippingFee);
		model.addAttribute("totalPrice", totalPrice);

		return "cart2";
	}
}