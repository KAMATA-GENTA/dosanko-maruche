package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

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
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import com.example.demo.service.ReviewService;

@Controller
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;
	private final ReviewService reviewService;
	private final CartService cartService;

	public ProductController(
			ProductService productService,
			ReviewService reviewService,
			CartService cartService) {

		this.productService = productService;
		this.reviewService = reviewService;
		this.cartService = cartService;
	}

	@GetMapping("/{productId}")
	public String showProductDetail(
			@PathVariable int productId,
			@RequestParam(name = "sort", defaultValue = "new") String sort,
			@RequestParam(name = "rating", required = false) Integer rating, Model model) {

		// 商品IDをもとに商品情報を1件取得する
		Product product = productService.findById(productId);

		// 商品のレビュー平均評価を取得する
		Double averageRating = reviewService.getAverageRating(productId);

		// レビューの並び順を正常な値に整える
		String selectedSort = reviewService.normalizeSort(sort);

		// レビューの星評価フィルターを正常な値に整える
		Integer selectedRating = reviewService.normalizeRating(rating);

		List<Review> reviews = reviewService.findByProduct(
				productId,
				selectedSort,
				selectedRating);

		// 商品情報を画面に渡す
		model.addAttribute("product", product);

		// 平均評価を画面に渡す
		model.addAttribute("averageRating", averageRating);

		// レビュー一覧を画面に渡す
		model.addAttribute("reviews", reviews);

		// レビュー投稿フォーム用の空のReviewオブジェクトを画面に渡す
		model.addAttribute("reviewForm", new Review());
		model.addAttribute("selectedSort", selectedSort);

		// 現在選択されている星評価フィルターを画面に戻す
		model.addAttribute("selectedRating", selectedRating);

		return "product-detail";
	}

	@PostMapping("/{productId}/review")
	public String postReview(
			@PathVariable int productId,
			@ModelAttribute("reviewForm") Review review,
			@SessionAttribute(value = "userId", required = false) Integer userId) {

		if (userId == null) {
			return "redirect:/login";
		}

		// 投稿されたレビューに商品IDをセットする
		review.setProductId(productId);
		review.setUserId(userId);

		// レビューを保存する
		reviewService.save(review);

		// 商品詳細画面のレビュー欄へ戻る
		return "redirect:/products/" + productId + "#review-section";
	}

	@PostMapping("/{productId}/cart")
	public String addCart(
			@PathVariable int productId,
			@RequestParam int quantity,
			HttpSession session) {

		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			return "redirect:/login";
		}

		cartService.addToCart(userId, productId, quantity);

		return "redirect:/products/" + productId;
	}
}