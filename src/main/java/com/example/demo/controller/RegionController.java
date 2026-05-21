package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Product;
import com.example.demo.entity.RankingProduct;
import com.example.demo.enums.Category;
import com.example.demo.enums.Region;
import com.example.demo.service.CharacterService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserServiceImpl;

@Controller
@RequestMapping("/region")
public class RegionController {

	private final UserServiceImpl userServiceImpl;

	private final ProductService productService;
	private final CharacterService characterService;

	public RegionController(ProductService productService, CharacterService characterService,
			UserServiceImpl userServiceImpl) {
		this.productService = productService;
		this.characterService = characterService;
		this.userServiceImpl = userServiceImpl;
	}

	/**
	 * 地域詳細ページ
	 *
	 * URLは /region/wakkanai のように地域名で受け取る。 ただし、DB検索にはregion_idが必要なので、Region
	 * enumからIDを取得して使う。
	 */
	@GetMapping("/{regionName}")
	public String showRegion(@PathVariable String regionName, Integer categoryId, Model model) {

		// URLの地域名からRegion enumを取得する
		Region region = Region.fromUrlName(regionName);

		// 存在しない地域名の場合はトップページへ戻す
		if (region == null) {
			return "redirect:/";
		}

		List<Product> products;

		// カテゴリ未選択の場合は、その地域の商品をすべて取得する
		if (categoryId == null) {
			products = productService.getProductsByRegionId(region.getId());
		} else {
			// カテゴリ選択時は、その地域の商品を取得してからカテゴリIDで絞り込む
			products = productService.getProductsByRegionId(region.getId()).stream()
					.filter(product -> categoryId.equals(product.getCategoryId())).toList();
		}

		// 地域ページ表示用
		model.addAttribute("region", region);

		// URL生成用。region.urlNameでも使えるが、HTML側で使いやすいように渡す
		model.addAttribute("regionName", region.getUrlName());

		// 商品一覧
		model.addAttribute("products", productService.findAllWithCategoryWithRegions(products));

		// 選択中カテゴリ
		model.addAttribute("selectedCategoryId", categoryId);

		// Category enumからカテゴリ一覧を渡す
		model.addAttribute("categories", Category.values());

		// 地域別・カテゴリ別ランキング
		Map<Category, List<RankingProduct>> regionRankingMap = Arrays.stream(Category.values())
				.collect(Collectors.toMap(Function.identity(), category -> productService
						.getRankingByRegionIdAndCategoryId(region.getId(), category.getId())));

		model.addAttribute("rankingMap", regionRankingMap);
		// キャラクター表示
		model.addAttribute("characterImage", characterService.getCharacterImageByRegion(region));

		model.addAttribute("orderDetailCount", characterService.getOrderDetailCountByRegion(region));

		return "region_test";
	}

}