package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			@RequestParam(name = "rating", required = false) Integer rating,
			Model model) {

		Product product = productService.findById(productId);
		Double averageRating = reviewService.getAverageRating(productId);

		String selectedSort = reviewService.normalizeSort(sort);
		Integer selectedRating = reviewService.normalizeRating(rating);

		List<Review> reviews = reviewService.findByProduct(
				productId,
				selectedSort,
				selectedRating);

		model.addAttribute("product", product);
		model.addAttribute("averageRating", averageRating);
		model.addAttribute("reviews", reviews);
		model.addAttribute("reviewForm", new Review());
		model.addAttribute("selectedSort", selectedSort);
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

		review.setProductId(productId);
		review.setUserId(userId);

		reviewService.save(review);

		return "redirect:/products/" + productId + "#review-section";
	}

	@PostMapping("/{productId}/cart")
	public String addCart(
			@PathVariable int productId,
			@RequestParam int quantity,
			HttpSession session) {

		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {

			@SuppressWarnings("unchecked")
			Map<Integer, Integer> guestCart = (Map<Integer, Integer>) session.getAttribute("guestCart");

			if (guestCart == null) {
				guestCart = new HashMap<>();
			}

			int currentQuantity = guestCart.getOrDefault(productId, 0);
			guestCart.put(productId, currentQuantity + quantity);

			session.setAttribute("guestCart", guestCart);

			return "redirect:/products/" + productId;
		}

		cartService.addToCart(userId, productId, quantity);

		return "redirect:/products/" + productId;
	}
}