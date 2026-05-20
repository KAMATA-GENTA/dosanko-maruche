package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.CartDisplayItem;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Product;
import com.example.demo.mapper.CartMapper;
import com.example.demo.mapper.OrderDetailMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.ProductService;
import com.example.demo.service.SettlementService;

@Controller
public class TreasureController {

	private final CartMapper cartMapper;
	private final OrderMapper orderMapper;
	private final OrderDetailMapper orderDetailMapper;
	private final ProductService productService;
	private final SettlementService settlementService;

	public TreasureController(
			CartMapper cartMapper,
			OrderMapper orderMapper,
			OrderDetailMapper orderDetailMapper,
			ProductService productService,
			SettlementService settlementService) {

		this.cartMapper = cartMapper;
		this.orderMapper = orderMapper;
		this.orderDetailMapper = orderDetailMapper;
		this.productService = productService;
		this.settlementService = settlementService;
	}

	@GetMapping("/treasure")
	public String showTreasureGame(HttpSession session) {

		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			return "redirect:/login";
		}

		return "treasure";
	}

	@Transactional
	@PostMapping("/treasure/result")
	public String receiveTreasureResult(String result, int selectedBox, int correctBox, HttpSession session,
			Model model) {

		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			return "redirect:/login";
		}

		List<CartItem> cartItems = cartMapper.findByUserId(userId);

		List<CartDisplayItem> displayItems = new ArrayList<>();


		for (CartItem cartItem : cartItems) {

			Product product = productService.findById(cartItem.getProductId());

			displayItems.add(
					new CartDisplayItem(cartItem, product));
		}

		boolean isFreeShipping = "hit".equals(result);

		int subtotal = settlementService.calcSubTotal(displayItems);

		int shippingFee = settlementService.calcShippingFee(isFreeShipping);

		int totalPrice = settlementService.calcTotalPrice(
				displayItems,
				isFreeShipping);

		Order order = new Order();

		order.setUserId(userId);
		order.setSubtotal(subtotal);
		order.setShippingFee(shippingFee);

		orderMapper.insert(order);

		for (CartItem cartItem : cartItems) {

			Product product = productService.findById(
					cartItem.getProductId());

			OrderDetail detail = new OrderDetail();

			detail.setOrderId(order.getId());
			detail.setProductId(cartItem.getProductId());
			detail.setQuantity(cartItem.getQuantity());
			detail.setPrice(product.getPrice());

			orderDetailMapper.insert(detail);
		}

		cartMapper.deleteByUserId(userId);

		model.addAttribute("result", result);
		model.addAttribute("subtotal", subtotal);
		model.addAttribute("shippingFee", shippingFee);
		model.addAttribute("totalPrice", totalPrice);

		return "purchase-complete";
	}
}