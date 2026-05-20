package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Product;
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
	@GetMapping("/{regionName}")
	public String showRegion(@PathVariable String regionName, @RequestParam(required = false) Integer categoryId,
			Model model) {

		Region region = getRegion(regionName);

		model.addAttribute("region", region);
		model.addAttribute("regionName", regionName);
		model.addAttribute("products",
				productService.getProductsByRegionIdAndCategoryId(region.getRegion_id(), categoryId));

		//キャラクター画像、地域idの値を渡して進化するかどうかの判定
		model.addAttribute("characterImage", characterService.getCharacterImageByRegion(region));
		model.addAttribute("orderDetailCount", characterService.getOrderDetailCountByRegion(region));

		return "region_test";
	}

	private Region getRegion(String regionName) {
		if (regionName.equals("sapporo")) {
			return Region.Sapporo;

		} else if (regionName.equals("hakodate")) {
			return Region.Hakodate;

		} else if (regionName.equals("wakkanai")) {
			return Region.Wakkanai;

		} else if (regionName.equals("kitami")) {
			return Region.Kitami;

		} else if (regionName.equals("obihiro")) {
			return Region.Obihiro;
		} else if (regionName.equals("otaru")) {
			return Region.Otaru;
			region = Region.Obihiro;
		}

		List<Product> products;

		if (categoryId == null) {
			products = productService.getAllProducts();
		} else {
			products = productService.getProductsByCategoryId(categoryId);
		}
		throw new IllegalArgumentException("存在しない地域です");
	}

}