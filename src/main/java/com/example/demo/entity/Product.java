package com.example.demo.entity;

import lombok.Data;

/**
 * productsテーブルと対応するエンティティクラス
 */
@Data
public class Product {

	private int productId; // 商品ID (PK)
	private String productName; // 商品名
	private String description; // 商品説明
	private int price; // 価格
	private int stockQuantity; // 在庫数
	private int regionId; // 地域ID (FK)
	private int categoryId; // カテゴリID (FK)
	private String imageUrl; // 画像のURLやファイルパス
	private double averageRating; // 平均評価（星の数）
	private int salesCount; // 販売数（ランキング用）
	private boolean isActive; // 取扱中フラグ（論理削除用）

}