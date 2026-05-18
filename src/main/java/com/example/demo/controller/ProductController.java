package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.entity.Product;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import com.example.demo.service.ReviewService;

@Controller
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;
	private final ReviewService reviewService;
	private final CartService cartService;

	public ProductController(ProductService productService,
			ReviewService reviewService,
			CartService cartService) {
		this.productService = productService;
		this.reviewService = reviewService;
		this.cartService = cartService;
	}

	// 商品詳細表示
	@GetMapping("/{productId}")
	public String showProductDetail(@PathVariable int productId,
			@RequestParam(name = "sort", defaultValue = "new") String sort,
			@RequestParam(name = "rating", required = false) Integer rating,
			Model model) {

		Product product = productService.findById(productId);
		Double averageRating = reviewService.getAverageRating(productId);

		String selectedSort = reviewService.normalizeSort(sort);
		Integer selectedRating = reviewService.normalizeRating(rating);
		List<Review> reviews = reviewService.findByProduct(productId, selectedSort, selectedRating);

		model.addAttribute("product", product);
		model.addAttribute("averageRating", averageRating);
		model.addAttribute("reviews", reviews);
		model.addAttribute("reviewForm", new Review());

		// レビューの表示条件を画面に戻すために使う
		model.addAttribute("selectedSort", selectedSort);
		model.addAttribute("selectedRating", selectedRating);

		return "product-detail";
	}

	// レビュー投稿
	@PostMapping("/{productId}/review")
	public String postReview(@PathVariable int productId,
			@ModelAttribute("reviewForm") Review review,
			@SessionAttribute(value = "loginUser", required = false) User user) {

		review.setProductId(productId);

		// ログイン機能が完成している場合はログイン中ユーザーのIDを使う。
		// まだ未ログインで動作確認する場合は、data.sqlに存在するユーザーID=1で登録する。
		if (user != null) {
			review.setUserId(user.getId());
		} else {
			review.setUserId(1);
		}

		reviewService.save(review);

		return "redirect:/product/" + productId + "#review-section";
	}

	// カート追加
	@PostMapping("/{productId}/cart")
	public String addToCart(@PathVariable int productId,
			@RequestParam int quantity,
			@SessionAttribute("loginUser") User user) {

		cartService.addToCart(user.getId(), productId, quantity);

		return "redirect:/cart";
	}
}
