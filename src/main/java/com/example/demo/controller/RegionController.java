package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.enums.Region;

@Controller
@RequestMapping("/region")
public class RegionController {

	//地域詳細ページ
	@GetMapping("/{regionName}")
	public String showRegion(
			@PathVariable String regionName,
			Model model) {

		Region region = null;

		if (regionName.equals("sapporo")) {
			region = Region.Sapporo;

		} else if (regionName.equals("hakodate")) {
			region = Region.Hakodate;

		} else if (regionName.equals("wakkanai")) {
			region = Region.Wakkanai;

		} else if (regionName.equals("kitami")) {
			region = Region.Kitami;

		} else if (regionName.equals("obihiro")) {
			region = Region.Obihiro;
		}

		model.addAttribute("region", region);

		return "region_test";
	}

}