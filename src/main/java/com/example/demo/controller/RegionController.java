package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Region;

@Controller
public class RegionController {

	// 地域詳細ページ
	@GetMapping("/sapporo")
	public String showSpporo(Model model) {

		Region region = new Region(1);

		model.addAttribute("region", region);

		return "region_test";
	}

	@GetMapping("/hakodate")
	public String showHokodate(Model model) {

		Region region = new Region(2);
		model.addAttribute("region", region);

		return "region_test";
	}

	@GetMapping("/wakkanai")
	public String showRegionWakkanai(Model model) {

		Region region = new Region(3);
		model.addAttribute("region", region);

		return "region_test";
	}

	@GetMapping("/kitami")
	public String showRegionKitami(Model model) {

		Region region = new Region(4);
		model.addAttribute("region", region);

		return "region_test";
	}

	@GetMapping("/obihiro")
	public String showRegionObihiro(Model model) {

		Region region = new Region(5);
		model.addAttribute("region", region);

		return "region_test";
	}

}