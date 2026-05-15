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
public class UserController {

	@Autowired
	private HttpSession session;

	// 実際の開発では、ここにUserServiceなどのビジネスロジックをInjectします
	// @Autowired
	// private UserService userService;

	// ==========================================
	// 1. 新規登録
	// ==========================================
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "user/register";
	}

	@PostMapping("/register")
	public String submitRegisterForm(@ModelAttribute UserForm form, Model model) {
		// TODO: パスワードをハッシュ化し、usersテーブルにINSERTする処理
		// userService.registerUser(form.getUsername(), form.getEmail(), form.getPassword());

		System.out.println("登録 username = " + form.getUsername());

		// 登録完了後はログイン画面へリダイレクト
		return "redirect:/login";
	}

	// ==========================================
	// 2. ログイン
	// ==========================================
	@GetMapping("/login")
	public String showLoginForm(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "user/login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute LoginForm form, Model model) {
		// TODO: emailとハッシュ化パスワードでDBを検索し、ユーザー情報を取得する処理
		// User user = userService.authenticate(form.getEmail(), form.getPassword());

		// 仮の認証ロジック（実際はDBのレコードと照合します）
		boolean isAuthenticated = true; // DBに存在すればtrue

		if (isAuthenticated) {
			// セッションにユーザーIDやユーザー名を保存
			session.setAttribute("userId", 1); // 仮のID
			session.setAttribute("username", "テストユーザー");
			return "redirect:/mypage";
		} else {
			model.addAttribute("error", "メールアドレスまたはパスワードが間違っています。");
			return "user/login";
		}
	}

	// ==========================================
	// 3. マイページ（ユーザー情報、購入履歴、地域別購入数）
	// ==========================================
	@GetMapping("/mypage")
	public String showMyPage(Model model) {
		// ログインチェック
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/login"; // 未ログインならログイン画面へ
		}

		// TODO: DBから必要な情報を取得してModelにセットする処理
		// 1. ユーザー基本情報
		// User user = userService.getUserById(userId);
		model.addAttribute("username", session.getAttribute("username"));

		// 2. 購入履歴（orders と order_details の結合データ）
		// List<OrderDto> orderHistory = userService.getOrderHistory(userId);
		// model.addAttribute("orderHistory", orderHistory);

		// 3. 各地域の購入回数（orders JOIN order_details JOIN products GROUP BY region_id のデータ）
		// List<RegionCountDto> regionCounts = userService.getRegionPurchaseCounts(userId);
		// model.addAttribute("regionCounts", regionCounts);

		return "user/mypage";
	}

	// ==========================================
	// 4. ログアウト
	// ==========================================
	@GetMapping("/logout")
	public String logout() {
		session.invalidate(); // セッションを破棄
		return "redirect:/login";
	}
}