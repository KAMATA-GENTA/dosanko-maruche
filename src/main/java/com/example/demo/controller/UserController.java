package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@Autowired
	private HttpSession session;

	private void addSessionAttributes(Model model) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		model.addAttribute("isLoggedIn", username != null);
	}

	// ==========================================
	// 1. マイページ（注文履歴）
	// ==========================================
	@GetMapping("/my-order-history")
	public String showMyPage(Model model) {
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/login";
		}
		addSessionAttributes(model);
		return "user/my-order-history";
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