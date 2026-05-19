package com.example.demo.entity;

import lombok.Data;

/**
 * ランキング表示用クラス
 * 商品情報 + 購入数を持つ
 */
@Data
public class RankingProduct {

	private Integer id;

	private String productName;

	private Integer price;

	private String imageUrl;

	private Integer regionId;

	private String regionName;

	// ランキング集計用の購入数
	private Integer totalQuantity;
}