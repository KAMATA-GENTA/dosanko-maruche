package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 地域情報を管理するenumクラス
 *
 * URLでは /region/wakkanai のように文字列を使い、
 * DB検索では products.region_id に対応するIDを使う。
 */
@AllArgsConstructor
@Getter
public enum Region {

    /*
     * 第1引数: DBのproducts.region_idと対応するID
     * 第2引数: URL用の地域名
     * 第3引数: 画面に表示する地域名
     * 第4引数: 地域説明
     * 第5引数: 地域画像ファイル名
     * 第6引数: 地域キャラクター
     * 第7引数: 背景色
     */
    SAPPORO(1, "sapporo", "札幌",
            "北海道の中心都市です。市町村の魅力度ランキング調査でも毎年上位にランクインしていて、海鮮だけでなく、スープカレーなども有名です",
            "Sapporo.png", Character.SapporoChan, "#dff5ff"),

    OTARU(6, "otaru", "小樽",
            "歴史的な石造りの倉庫群が残る「小樽運河」をはじめ、ガラス工芸品、オルゴール、そして新鮮な海鮮を楽しめる観光地です",
            "Otaru.png", Character.OtaruChan, "#d6f0ff"),

    HAKODATE(2, "hakodate", "函館",
            "海鮮が有名です。また、夜景の名所として函館山や五稜郭が知られています",
            "Hakodate.png", Character.HakodateChan, "#1b2b52"),

    KITAMI(3, "kitami", "北見",
            "玉ねぎの生産量・出荷量、白花豆の生産量が日本一となっており、ホタテ漁が盛んで「ホタテ養殖発祥の地」にもなっています",
            "Kitami.png", Character.KitamiChan, "#fff1cc"),

    OBIHIRO(5, "obihiro", "帯広",
            "道東で最大の人口を擁する、十勝地方の中心都市です。豚丼がとても有名です",
            "Obihiro.png", Character.ObihiroChan, "#ffe0cc"),

    WAKKANAI(4, "wakkanai", "稚内",
            "北海道最北の都市です。タコしゃぶなどが有名で、ご当地グルメとしてチャーメンも人気です",
            "Wakkanai.png", Character.WakkanaiChan, "#d9f2ff");

    // DBのproducts.region_idと対応するID
    private final Integer id;

    // URL用の地域名。例: /region/wakkanai
    private final String urlName;

    // 画面に表示する地域名。例: 稚内
    private final String name;

    // 地域紹介文
    private final String description;

    // 地域画像ファイル名
    private final String regionImage;

    // 地域キャラクター
    private final Character character;

    // 地域ページの背景色
    private final String backgroundColor;

    /**
     * DBの地域IDからRegionを取得する。
     */
    public static Region fromId(Integer id) {
        if (id == null) {
            return null;
        }

        for (Region region : Region.values()) {
            if (region.getId().equals(id)) {
                return region;
            }
        }

        return null;
    }

    /**
     * URLの地域名からRegionを取得する。
     * 例: "wakkanai" → WAKKANAI
     */
    public static Region fromUrlName(String urlName) {
        if (urlName == null) {
            return null;
        }

        for (Region region : Region.values()) {
            if (region.getUrlName().equalsIgnoreCase(urlName)) {
                return region;
            }
        }

        return null;
    }

    /**
     * 地域IDから表示用の地域名を取得する。
     */
    public static String getNameById(Integer id) {
        Region region = fromId(id);
        return region != null ? region.getName() : "不明";
    }
}