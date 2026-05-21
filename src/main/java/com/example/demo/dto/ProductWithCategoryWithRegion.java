package com.example.demo.dto;

import lombok.Data;

@Data
public class ProductWithCategoryWithRegion {

	// product
	private Integer id;
	private String name;
	private String description;
	private Integer price;
	private String imageUrl;

	// category
	private Integer categoryId;
	private String categoryName;

	// region
	private Integer regionId;
	private String regionName;
}