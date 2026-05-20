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
import com.example.demo.entity.User;
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

		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "redirect:/login";
		}

		int userId = loginUser.getId();

		List<CartItem> cartItems = cartMapper.findByUserId(userId);

		int subtotal = settlementService.calcSubtotal(cartItems);

		boolean isHit = "hit".equals(result);

		int finalShippingFee = settlementService.calcShippingFee(isHit);

		int totalPrice = settlementService.calcTotalPrice(subtotal, finalShippingFee);

		Order order = new Order();
		order.setUserId(userId);
		order.setSubtotal(subtotal);
		order.setShippingFee(finalShippingFee);

		orderMapper.insert(order);

		for (CartItem cartItem : cartItems) {

			Product product = productService.findById(cartItem.getProductId());

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
		model.addAttribute("shippingFee", finalShippingFee);
		model.addAttribute("totalPrice", totalPrice);

		return "purchase-complete";
	}
}