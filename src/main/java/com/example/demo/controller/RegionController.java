package com.example.demo.controller;

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

        List<Product> products;

        // カテゴリ未選択の場合は、その地域の商品を取得します。
        if (categoryId == null) {
            products = productService.getProductsByRegionId(regionId);
        } else {
            // カテゴリ選択時は、地域の商品を取得してからカテゴリIDで絞り込みます。
            products = productService.getProductsByRegionId(regionId)
                    .stream()
                    .filter(product -> categoryId.equals(product.getCategoryId()))
                    .toList();
        }

        model.addAttribute("region", region);
        model.addAttribute("regionId", regionId);
        model.addAttribute("products", products);
        model.addAttribute("selectedCategoryId", categoryId);
        model.addAttribute("categories", Category.values());

        return "region_test";
    }
}