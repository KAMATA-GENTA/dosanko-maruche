package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Region;

@Controller
@RequestMapping("/region")
public class RegionController {

	// 地域詳細ページ
	@GetMapping("/sapporo")
	public String showSpporo(@ModelAttribute Region region, Model model) {

		Region region1 = new Region(1, "札幌", "北海道の中心都市です");
		model.addAttribute("region", region1);
		return "region_test";
	}

	@GetMapping("/hakodate")
	public String showhakodate(@ModelAttribute Region region, Model model) {

		Region region2 = new Region(2, "函館", "夜景と海鮮が有名です");
		model.addAttribute("region", region2);
		return "region_test";
	}

	@GetMapping("/wakkanai")
	public String showRegionWakkanai(Model model) {

		Region region3 = new Region(3, "稚内", "北海道最北の都市です");
		model.addAttribute("region", region3);
		model.addAttribute("region", region3);

		return "region_test";
	}

	@GetMapping("/kitami")
	public String showRegionKitami(Model model) {

		Region region4 = new Region(4, "北見", "テーマパークがあります");
		model.addAttribute("region", region4);

		return "region_test";
	}

	@GetMapping("/obihiro")
	public String showRegionObihiro(Model model) {

		Region region = new Region(5, "帯広", "豚丼が有名です");
		model.addAttribute("region", region);

		return "region_test";
	}

}