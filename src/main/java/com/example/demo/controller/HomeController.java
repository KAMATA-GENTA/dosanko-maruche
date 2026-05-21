package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Product;
import com.example.demo.entity.RankingProduct;
import com.example.demo.enums.Category;
import com.example.demo.enums.Character;
import com.example.demo.enums.Region;
import com.example.demo.service.ProductService;

@Controller
public class HomeController {

	private final ProductService productService;

	public HomeController(ProductService productService) {
		this.productService = productService;
	}

	// ホーム画面を表示する
	@GetMapping({ "/", "/index" })
	public String home(@RequestParam(required = false) Integer categoryId, Model model, HttpSession session) {

		String username = (String) session.getAttribute("username");
		if (username != null) {
			// ユーザー名が存在すれば、ログイン中としてデータを画面に渡す
			model.addAttribute("isLoggedIn", true);
			model.addAttribute("username", username);
		} else {
			// 存在しなければ未ログイン
			model.addAttribute("isLoggedIn", false);
		}

		List<Product> products;

		// カテゴリが選択されていない場合は全商品を取得する
		if (categoryId == null) {
			products = productService.getAllProducts();
			// カテゴリが選択されている場合は、そのカテゴリの商品だけ取得する
		} else {
			products = productService.getProductsByCategoryId(categoryId);
		}

		// 商品一覧を画面に渡す
		model.addAttribute("products", productService.findAllWithCategoryWithRegions(products));
		// 選択中カテゴリを画面に渡す
		model.addAttribute("selectedCategoryId", categoryId);
		model.addAttribute("charctors", Character.values());
		// HTMLで地域リンクをenumから作るために渡す
		model.addAttribute("regions", Region.values());
		// HTMLでカテゴリリンクをenumから作るために渡す
		model.addAttribute("categories", Category.values());

		// カテゴリIDを直書きせず、Category enumから取得する
		Map<Category, List<RankingProduct>> rankingMap = Arrays.stream(Category.values()).collect(Collectors
				.toMap(Function.identity(), category -> productService.getRankingByCategoryId(category.getId())));

		model.addAttribute("rankingMap", rankingMap);

		return "index";
	}

	// 指定カテゴリのランキングをModelに追加する
	private List<RankingProduct> addRankingToModel(Model model, Category category) {
		List<RankingProduct> rankingProducts = productService.getRankingByCategoryId(category.getId());

		return rankingProducts;
	}
}