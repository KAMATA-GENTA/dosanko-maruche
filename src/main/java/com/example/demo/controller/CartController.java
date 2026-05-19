package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.entity.CartDisplayItem;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.mapper.CartMapper;
import com.example.demo.service.ProductService;

@Controller
public class CartController {

	private final CartMapper cartMapper;
	private final ProductService productService;

	public CartController(CartMapper cartMapper, ProductService productService) {
		this.cartMapper = cartMapper;
		this.productService = productService;
	}

	@GetMapping("/cart")
	public String showCart(
			Model model,
			@SessionAttribute(value = "loginUser", required = false) User user) {

		if (user == null) {
			return "redirect:/login";
		}

		List<CartItem> cartItems = cartMapper.findByUserId(user.getId());

		List<CartDisplayItem> displayItems = new ArrayList<>();

		for (CartItem cartItem : cartItems) {
			Product product = productService.findById(cartItem.getProductId());
			displayItems.add(new CartDisplayItem(cartItem, product));
		}

		int subtotal = displayItems.stream()
				.mapToInt(CartDisplayItem::getSubtotal)
				.sum();

		int shippingFee = 800;

		model.addAttribute("cartItems", displayItems);
		model.addAttribute("subtotal", subtotal);
		model.addAttribute("shippingFee", shippingFee);
		model.addAttribute("totalPrice", subtotal + shippingFee);

		return "cart2";
	}
}