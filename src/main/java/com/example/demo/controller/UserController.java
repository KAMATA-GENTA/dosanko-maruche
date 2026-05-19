package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.mapper.UserMapper;

@Controller
public class UserController {

	@Autowired
	private HttpSession session;
	@Autowired
	private UserMapper userMapper;

	private void addSessionAttributes(Model model) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		model.addAttribute("isLoggedIn", username != null);
	}

	// ==========================================
	// 1. マイページ（注文履歴）
	// ==========================================
	@GetMapping("/my-page")
	public String showMyPage(Model model) {
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null)
			return "redirect:/login";

		addSessionAttributes(model);

		List<Order> orders = userMapper.findOrdersByUserId(userId);

		// 注文ごとに明細リストを取得してMapに格納
		Map<Integer, List<OrderDetail>> orderDetailsMap = new LinkedHashMap<>();
		for (Order order : orders) {
			List<OrderDetail> details = userMapper.findOrderDetailsByOrderId(order.getId());
			orderDetailsMap.put(order.getId(), details);
		}

		model.addAttribute("user", userMapper.findById(userId));
		model.addAttribute("orders", orders);
		model.addAttribute("orderDetailsMap", orderDetailsMap);
		model.addAttribute("regionCounts", userMapper.findRegionCountByUserId(userId));

		return "user/my-page";
	}

	// ==========================================
	// 2. プロフィール情報
	// ==========================================
	@GetMapping("/profile-information")
	public String showProfileInformation(Model model) {
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/login";
		}
		addSessionAttributes(model);
		return "user/profile-information";
	}
}