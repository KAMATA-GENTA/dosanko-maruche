package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.User;
import com.example.demo.form.LoginForm;
import com.example.demo.form.UserForm;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;

@Controller
public class AuthController {

	@Autowired
	private HttpSession session;
	@Autowired
	private UserService userService;
	@Autowired
	private UserMapper userMapper;
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
		// DBにINSERTする処理
		userService.register(form);
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

		// DBからメールアドレスでユーザを検索する
		User user = userMapper.findByEmail(form.getEmail());

		// data.sql のパスワードは平文なので、まず平文で比較する
		// ※ sign-up から登録したユーザはBCryptハッシュなので matches() で照合
		boolean isAuthenticated = false;
		if (user != null) {
			String stored = user.getPasswordHash();
			if (stored.startsWith("$2a$") || stored.startsWith("$2b$")) {
				// BCryptハッシュ → matches() で照合
				isAuthenticated = passwordEncoder.matches(form.getPassword(), stored);
			} else {
				// 平文（data.sqlの初期データ）→ equals() で照合
				isAuthenticated = stored.equals(form.getPassword());
			}
		}

		if (isAuthenticated) {
			session.setAttribute("userId", user.getId());
			session.setAttribute("username", user.getUsername()); // DBから取得
			session.setAttribute("loginUser", user);
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
		return "redirect:/";
	}
}