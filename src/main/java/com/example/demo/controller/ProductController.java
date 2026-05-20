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
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;
	private final ReviewService reviewService;
	private final CartService cartService;

	public ProductController(ProductService productService, ReviewService reviewService, CartService cartService) {
		this.productService = productService;
		this.reviewService = reviewService;
		this.cartService = cartService;
	}

	// 商品詳細画面を表示する
	// 例: /products/1にアクセスされたときに商品IDが1の詳細を表示する
	@GetMapping("/{productId}")
	public String showProductDetail(@PathVariable int productId,
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

		// 商品ID、並び順、星評価フィルターをもとにレビュー一覧を取得する
		List<Review> reviews = reviewService.findByProduct(productId, selectedSort, selectedRating);

		// 商品情報を画面に渡す
		model.addAttribute("product", product);

		// 平均評価を画面に渡す
		model.addAttribute("averageRating", averageRating);

		// レビュー一覧を画面に渡す
		model.addAttribute("reviews", reviews);

		// レビュー投稿フォーム用の空のReviewオブジェクトを画面に渡す
		model.addAttribute("reviewForm", new Review());

		// 現在選択されている並び順を画面に戻す
		model.addAttribute("selectedSort", selectedSort);

		// 現在選択されている星評価フィルターを画面に戻す
		model.addAttribute("selectedRating", selectedRating);

		return "product-detail";
	}

	// レビューを投稿する
	// 例: /products/1/review にPOSTされたときに、商品ID1にレビューを登録する
	@PostMapping("/{productId}/review")
	public String postReview(@PathVariable int productId, @ModelAttribute("reviewForm") Review review,
			@SessionAttribute(value = "loginUser", required = false) User user) {

		// 投稿されたレビューに商品IDをセットする
		review.setProductId(productId);

		// ログインしている場合はログイン中ユーザーのIDを使う
		if (user != null) {
			review.setUserId(user.getId());
			// ログイン機能が未完成、または未ログインで動作確認する場合はデモ用ユーザーID=1を使う
		} else {
			review.setUserId(1);
		}

		// レビューを保存する
		reviewService.save(review);

		// 商品詳細画面のレビュー欄へ戻る
		return "redirect:/products/" + productId + "#review-section";
	}

	// 商品をカートに追加する
	// 例: /products/1/cart にPOSTされたときに、商品ID1をカートへ追加する
	@PostMapping("/{productId}/cart")
	public String addToCart(@PathVariable int productId, @RequestParam int quantity,
			//// @SessionAttribute("loginUser") User user) { testo用コメントアウト
			//
			//
			//
			// cartService.addToCart(user.getId(), productId, quantity);
			@SessionAttribute(value = "loginUser", required = false) User user) {

		int userId;

		// ログインしている場合はログイン中ユーザーのIDを使う
		if (user != null) {
			userId = user.getId();

			// 未ログインで動作確認する場合はデモ用ユーザーID=1を使う
		} else {
			userId = 1;
		}

		// 商品ID、ユーザーID、数量をもとにカートへ追加する
		cartService.addToCart(userId, productId, quantity);

		// カート追加後、商品詳細画面に戻る
		return "redirect:/products/{productId}";
	}
}
