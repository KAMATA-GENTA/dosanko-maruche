package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Product;
import com.example.demo.entity.RankingProduct;
import com.example.demo.service.ProductService;

@Controller
public class HomeController {

	private final ProductService productService;

	public HomeController(ProductService productService) {
		this.productService = productService;
	}

	// ホーム画面を表示する
	@GetMapping("/")
	public String home(
			@RequestParam(required = false) Integer categoryId,
			Model model) {

		List<Product> products;

		// カテゴリが選択されていない場合は全商品を取得する
		if (categoryId == null) {
			products = productService.getAllProducts();
			// カテゴリが選択されている場合は、そのカテゴリの商品だけ取得する
		} else {
			products = productService.getProductsByCategoryId(categoryId);
		}

		// 商品一覧を画面に渡す
		model.addAttribute("products", products);
		// 選択中カテゴリを画面に渡す
		model.addAttribute("selectedCategoryId", categoryId);

		// 各カテゴリのランキング商品を取得する
		List<RankingProduct> seafoodRanking = productService.getRankingByCategoryId(1);
		List<RankingProduct> meatRanking = productService.getRankingByCategoryId(2);
		List<RankingProduct> vegetableRanking = productService.getRankingByCategoryId(3);
		List<RankingProduct> souvenirRanking = productService.getRankingByCategoryId(4);

		// ランキング情報を画面に渡す
		model.addAttribute("seafoodRanking", seafoodRanking);
		model.addAttribute("meatRanking", meatRanking);
		model.addAttribute("vegetableRanking", vegetableRanking);
		model.addAttribute("souvenirRanking", souvenirRanking);

		return "home";
	}
}