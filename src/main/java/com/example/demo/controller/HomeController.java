package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@Controller
public class HomeController {

	private final ProductService productService;

	public HomeController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/")
	public String home(@RequestParam(required = false) Integer categoryId, Model model) {

		List<Product> products;

		if (categoryId == null) {
			products = productService.getAllProducts();
		} else {
			products = productService.getProductsByCategoryId(categoryId);
		}

		model.addAttribute("products", products);
		model.addAttribute("selectedCategoryId", categoryId);

		return "index";
	}
}
