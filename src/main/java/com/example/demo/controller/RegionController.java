package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Region;

@Controller
@RequestMapping("/region")
public class RegionController {

	// 地域詳細ページ
	@GetMapping("/sapporo")
	public String showSpporo(Model model) {
		model.addAttribute("region", Region.Sapporo);

		return "region_test";
	}

	@GetMapping("/hakodate")
	public String showhakodate(Model model) {

		model.addAttribute("region", Region.Hakodate);
		return "region_test";
	}

	@GetMapping("/wakkanai")
	public String showRegionWakkanai(Model model) {

		model.addAttribute("region", Region.Wakkanai);

		return "region_test";
	}

	@GetMapping("/kitami")
	public String showRegionKitami(Model model) {
		model.addAttribute("region", Region.Kitami);

		return "region_test";
	}

	@GetMapping("/obihiro")
	public String showRegionObihiro(Model model) {

		model.addAttribute("region", Region.Obihiro);

		return "region_test";
	}

}