package com.example.dosanko.entity;

import lombok.Data;

/**
 * productsテーブルと対応するエンティティクラス
 */
@Data
public class Products {

	private int productId; // 商品ID (PK)
	private String productName; // 商品名
	private String description; // 商品説明
	private int price; // 価格
	private int categoryId; // カテゴリID (FK)
	private String imageUrl; // 画像のURLやファイルパス

}