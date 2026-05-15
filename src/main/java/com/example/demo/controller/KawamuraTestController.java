package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KawamuraTestController {

	@GetMapping("/kawamura")
	public String test() {
		return "kawamura-test";
	}
}