package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Theme {
	WINTER("winter"), HAKODATE("hakodate"), OTARU("otaru");

	private String name;
}
