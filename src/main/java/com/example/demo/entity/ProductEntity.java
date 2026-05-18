package com.example.demo.entity;

import lombok.Data;

// getter/setterなどを自動生成するLombokのアノテーション
@Data
public class ProductEntity {

	private Integer id;
	private Integer regionId;
	private Integer categoryId;
	private String productName;
	private Integer price;
	private String description;
	private String imageUrl;
}