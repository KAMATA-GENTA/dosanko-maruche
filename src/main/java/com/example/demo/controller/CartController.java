package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.CartItem;
import com.example.demo.mapper.CartMapper;

@Controller
public class CartController {

	private final CartMapper cartMapper;

	public CartController(CartMapper cartMapper) {
		this.cartMapper = cartMapper;
	}

	@GetMapping("/cart")
	public String showCart(Model model, HttpSession session) {

		// ログイン機能がまだなら仮で userId = 1
		Integer userId = 1;

		session.setAttribute("userId", userId);

		List<CartItem> cartItems = cartMapper.findByUserId(userId);

		int subtotal = cartItems.stream().mapToInt(CartItem::getSubtotal).sum();

		int shippingFee = 800;

		session.setAttribute("subtotal", subtotal);
		session.setAttribute("shippingFee", shippingFee);

		model.addAttribute("cartItems", cartItems);
		model.addAttribute("subtotal", subtotal);
		model.addAttribute("shippingFee", shippingFee);
		model.addAttribute("totalPrice", subtotal + shippingFee);

		return "cart2";
	}
}