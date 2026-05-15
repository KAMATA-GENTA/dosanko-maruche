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
			Model model) {

		Product product = productService.findById(productId);
		Double averageRating = reviewService.getAverageRating(productId);
		List<Review> reviews = reviewService.findByProduct(productId);

		model.addAttribute("product", product);
		model.addAttribute("averageRating", averageRating);
		model.addAttribute("reviews", reviews);
		model.addAttribute("reviewForm", new Review());

		return "product-detail";
	}

	// レビュー投稿
	@PostMapping("/{productId}/review")
	public String postReview(@PathVariable int productId,
			@ModelAttribute Review review
	//, @SessionAttribute("loginUser") User user
	) {

		review.setProductId(productId);

		//開発現在ログイン関係のcontrollerがないため、仮で”１”としておく。
		review.setUserId(1);

		reviewService.save(review);

		return "redirect:/product/" + productId;
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