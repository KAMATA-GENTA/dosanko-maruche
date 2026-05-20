package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * カテゴリ情報を管理するenum
 * DBにcategoriesテーブルを作らず、Java側でカテゴリIDとカテゴリ名を管理する
 */

// 全フィールドのコンストラクタ生成
@AllArgsConstructor
@Getter
public enum Category {

    // DBのproducts.category_idと対応させる
    SEAFOOD(1, "海産物", "seafoodRanking"),
    VEGETABLE(2, "農産物", "vegetableRanking"),
    MEAT(3, "畜産物", "meatRanking"),
    SOUVENIR(4, "お土産", "souvenirRanking");

    // DBに保存されているカテゴリID
    private final Integer id;

    // 画面に表示するカテゴリ名
    private final String name;

    // ランキングをModelに入れるときの名前
    private final String rankingModelName;

    /**
     * category_idからCategoryを取得するメソッド
     */
    public static Category fromId(Integer id) {
        if (id == null) {
            return null;
        }

        for (Category category : Category.values()) {
            if (category.getId().equals(id)) {
                return category;
            }
        }

        return null;
    }

    /**
     * category_idからカテゴリ名を取得するメソッド
     */
    public static String getNameById(Integer id) {
        Category category = fromId(id);
        return category != null ? category.getName() : "不明";
    }
}