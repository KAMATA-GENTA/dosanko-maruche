package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.LoginForm;
import com.example.demo.form.UserForm;

@Controller
public class AuthController {

	@Autowired
	private HttpSession session;

	private void addSessionAttributes(Model model) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		model.addAttribute("isLoggedIn", username != null);
	}

	// ==========================================
	// 1. 新規登録
	// ==========================================
	@GetMapping("/sign-up")
	public String showRegisterForm(Model model) {
		addSessionAttributes(model);
		model.addAttribute("userForm", new UserForm());
		return "user/sign-up";
	}

	@PostMapping("/sign-up")
	public String submitRegisterForm(@ModelAttribute UserForm form) {
		// TODO: DBにINSERTする処理
		System.out.println("登録 username = " + form.getUsername());
		return "redirect:/login";
	}

	// ==========================================
	// 2. ログイン
	// ==========================================
	@GetMapping("/login")
	public String showLoginForm(Model model) {
		addSessionAttributes(model);
		model.addAttribute("loginForm", new LoginForm());
		return "user/login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute LoginForm form, Model model) {
		// TODO: DBのemail/passwordと照合する処理
		boolean isAuthenticated = true; // 仮

		if (isAuthenticated) {
			session.setAttribute("userId", 1);
			session.setAttribute("username", "テストユーザー");
			return "redirect:/";
		} else {
			model.addAttribute("error", "メールアドレスまたはパスワードが間違っています。");
			addSessionAttributes(model);
			return "user/login";
		}
	}

	// ==========================================
	// 3. ログアウト
	// ==========================================
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/login";
	}
}
