package com.example.demo.controller;

import java.util.Map;

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
import com.example.demo.service.CartService;
import com.example.demo.service.UserService;

@Controller
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private CartService cartService;

	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	private void addSessionAttributes(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		model.addAttribute("isLoggedIn", username != null);
	}

	@GetMapping("/sign-up")
	public String showRegisterForm(Model model, HttpSession session) {
		addSessionAttributes(model, session);
		model.addAttribute("userForm", new UserForm());
		return "user/sign-up";
	}

	@PostMapping("/sign-up")
	public String submitRegisterForm(@ModelAttribute UserForm form) {
		userService.register(form);
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String showLoginForm(Model model, HttpSession session) {
		addSessionAttributes(model, session);
		model.addAttribute("loginForm", new LoginForm());
		return "user/login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute LoginForm form, Model model, HttpSession session) {

		User user = userMapper.findByEmail(form.getEmail());

		boolean isAuthenticated = false;

		if (user != null) {
			String stored = user.getPasswordHash();

			if (stored.startsWith("$2a$") || stored.startsWith("$2b$")) {
				isAuthenticated = passwordEncoder.matches(form.getPassword(), stored);
				System.out.println(isAuthenticated);
			} else {
				isAuthenticated = stored.equals(form.getPassword());
			}
		}

		if (isAuthenticated) {
			session.setAttribute("userId", user.getId());
			session.setAttribute("username", user.getUsername());

			@SuppressWarnings("unchecked")
			Map<Integer, Integer> guestCart = (Map<Integer, Integer>) session.getAttribute("guestCart");

			cartService.mergeGuestCart(user.getId(), guestCart);

			session.removeAttribute("guestCart");

			return "redirect:/my-page";
		}

		model.addAttribute("error", "メールアドレスまたはパスワードが間違っています。");
		addSessionAttributes(model, session);
		return "user/login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}