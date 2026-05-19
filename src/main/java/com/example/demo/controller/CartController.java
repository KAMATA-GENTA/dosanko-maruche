package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.User;
import com.example.demo.mapper.CartMapper;

@Controller
public class CartController {

	private final CartMapper cartMapper;

	public CartController(CartMapper cartMapper) {
		this.cartMapper = cartMapper;
	}

	@GetMapping("/cart")
	public String showCart(
			Model model,
			@SessionAttribute(value = "loginUser", required = false) User user) {

		if (user == null) {
			return "redirect:/login";
		}

		int userId = user.getId();

		List<CartItem> cartItems = cartMapper.findByUserId(userId);

		int subtotal = cartItems.stream()
				.mapToInt(CartItem::getSubtotal)
				.sum();

		int shippingFee = 800;

		model.addAttribute("cartItems", cartItems);
		model.addAttribute("subtotal", subtotal);
		model.addAttribute("shippingFee", shippingFee);
		model.addAttribute("totalPrice", subtotal + shippingFee);

		return "cart2";
	}

	@PostMapping("/cart/delete/{cartItemId}")
	public String deleteCartItem(
			@PathVariable int cartItemId,
			@SessionAttribute(value = "loginUser", required = false) User user) {

		if (user == null) {
			return "redirect:/login";
		}

		cartMapper.deleteByIdAndUserId(cartItemId, user.getId());

		return "redirect:/cart";
	}
}