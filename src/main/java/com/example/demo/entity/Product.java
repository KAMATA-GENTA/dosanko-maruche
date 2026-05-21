package com.example.demo.entity;

import lombok.Data;

/**
 * productsテーブルと対応するエンティティクラス
 */
@Data
public class Product {

	private Integer id; // 商品ID

	private String name; // 商品名

	private String description; // 商品説明

	private Integer price; // 価格

	private Integer categoryId; // DBのカテゴリID

	private String imageUrl; // 商品画像

	private Integer regionId; // 地域ID
}