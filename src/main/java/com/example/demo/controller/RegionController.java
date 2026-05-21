package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Product;
import com.example.demo.enums.Category;
import com.example.demo.enums.Region;
import com.example.demo.service.CharacterService;
import com.example.demo.service.ProductService;

@Controller
@RequestMapping("/region")
public class RegionController {

	private final ProductService productService;
	private final CharacterService characterService;

	public RegionController(ProductService productService, CharacterService characterService) {
		this.productService = productService;
		this.characterService = characterService;
	}

	// 地域詳細ページ
    @GetMapping("/{regionId}")
    public String showRegion(
            @PathVariable Integer regionId,
            Integer categoryId,
            Model model) {

        // URLの地域IDからRegion enumを取得します。
        Region region = Region.fromId(regionId);

        // 存在しない地域IDの場合はトップページへ戻します。
        if (region == null) {
            return "redirect:/";
        }

        // ★Listを型に解決できませんというエラー
        List<Product> products;

        // カテゴリ未選択の場合は、その地域の商品を取得します。
        if (categoryId == null) {
    		products = productService.getProductsByRegionId(region.getId());
		} else {
    	// カテゴリ選択時は、地域の商品を取得してからカテゴリIDで絞り込みます。
    	products = productService.getProductsByRegionId(region.getId())
            .stream()
            .filter(product -> categoryId.equals(product.getCategoryId()))
            .toList();
		}
		// カテゴリ別ランキング 地域ごと
		model.addAttribute("seafoodRanking",
        	productService.getRankingByRegionIdAndCategoryId(region.getId(), 1));

		model.addAttribute("vegetableRanking",
        	productService.getRankingByRegionIdAndCategoryId(region.getId(), 2));

		model.addAttribute("meatRanking",
        	productService.getRankingByRegionIdAndCategoryId(region.getId(), 3));

		model.addAttribute("souvenirRanking",
        	productService.getRankingByRegionIdAndCategoryId(region.getId(), 4));

		// キャラクター画像、地域idの値を渡して進化するかどうかの判定
		model.addAttribute("characterImage", characterService.getCharacterImageByRegion(region));
		model.addAttribute("orderDetailCount", characterService.getOrderDetailCountByRegion(region));

        model.addAttribute("region", region);
        model.addAttribute("regionId", regionId);
        model.addAttribute("products", products);
        model.addAttribute("selectedCategoryId", categoryId);
        model.addAttribute("categories", Category.values());

        return "region_test";
    }
}