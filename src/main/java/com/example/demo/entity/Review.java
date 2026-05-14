package com.example.demo.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * reviewsテーブルと対応するエンティティクラス
 */
@Data
public class Review {

	private int reviewId; // レビューID (PK)
	private int productId; // 商品ID (FK)
	private int userId; // ユーザーID (FK)
	private int rating; // 評価（星の数）
	private String comment; // コメント
	private LocalDateTime createdAt; // 作成日時
}
