package com.example.demo.entity;

import lombok.Data;

/**
 * productsテーブルと対応するエンティティクラス
 */
@Data
public class Product {

	private int id; // 商品ID (PK)
	private String name; // 商品名
	private String description; // 商品説明
	private int price; // 価格
	private int categoryId; // カテゴリID (FK)
	private String imageUrl; // 画像のURLやファイルパス
	private int regionId; // 地域ID (FK)

}