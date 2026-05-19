package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Product;
import com.example.demo.mapper.CartMapper;
import com.example.demo.mapper.OrderDetailMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.ProductService;

@Controller
public class TreasureController {

	private final CartMapper cartMapper;
	private final OrderMapper orderMapper;
	private final OrderDetailMapper orderDetailMapper;
	private final ProductService productService;

	public TreasureController(
			CartMapper cartMapper,
			OrderMapper orderMapper,
			OrderDetailMapper orderDetailMapper,
			ProductService productService) {

		this.cartMapper = cartMapper;
		this.orderMapper = orderMapper;
		this.orderDetailMapper = orderDetailMapper;
		this.productService = productService;
	}

	@GetMapping("/treasure")
	public String showTreasureGame() {
		return "treasure";
	}

	@Transactional
	@PostMapping("/treasure/result")
	public String receiveTreasureResult(
			String result,
			int selectedBox,
			int correctBox,
			HttpSession session,
			Model model) {

		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			userId = 1;
			session.setAttribute("userId", userId);
		}

		List<CartItem> cartItems = cartMapper.findByUserId(userId);

		// subtotal計算
		int subtotal = 0;

		for (CartItem cartItem : cartItems) {

			Product product = productService.findById(cartItem.getProductId());

			subtotal += product.getPrice() * cartItem.getQuantity();
		}

		int baseShippingFee = 800;

		boolean isHit = "hit".equals(result);

		int finalShippingFee = isHit ? 0 : baseShippingFee;

		int totalPrice = subtotal + finalShippingFee;

		// 注文保存
		Order order = new Order();
		order.setUserId(userId);
		order.setSubtotal(subtotal);
		order.setShippingFee(finalShippingFee);

		orderMapper.insert(order);

		// 注文明細保存
		for (CartItem cartItem : cartItems) {

			Product product = productService.findById(cartItem.getProductId());

			OrderDetail detail = new OrderDetail();

			detail.setOrderId(order.getId());
			detail.setProductId(cartItem.getProductId());
			detail.setQuantity(cartItem.getQuantity());

			// Productから価格取得
			detail.setPrice(product.getPrice());

			orderDetailMapper.insert(detail);
		}

		// カート削除
		cartMapper.deleteByUserId(userId);

		model.addAttribute("result", result);
		model.addAttribute("subtotal", subtotal);
		model.addAttribute("shippingFee", finalShippingFee);
		model.addAttribute("totalPrice", totalPrice);

		return "purchase-complete";
	}
}