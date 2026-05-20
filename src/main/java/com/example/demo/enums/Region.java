package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 地域情報を管理するenumクラス
 *
 * DBにregionsテーブルを作らない代わりに、Java側で地域IDと地域名などをまとめて管理する
 * productsテーブルには region_id だけを保存し、画面に表示する地域名はこのenumから取得する
 */
@AllArgsConstructor
@Getter
public enum Region {

    /*
     * enumの定数
     *
     * 第1引数: DBのproducts.region_idと対応するID
     * 第2引数: 画面に表示する地域名
     * 第3引数: 地域説明
     * 第4引数: 地域画像ファイル名
     * 第5引数: 地域キャラクター
     */
    SAPPORO(1, "札幌", "北海道の中心都市です", "Sapporo.png", Character.SapporoChan),
    OTARU(2, "小樽", "歴史的な石造りの倉庫群が残る「小樽運河」をはじめ、ガラス工芸品、オルゴール、そして新鮮な海鮮を楽しめる観光地です", "Otaru.png", Character.OtaruChan),
    HAKODATE(3, "函館", "夜景と海鮮が有名です", "Hakodate.png", Character.HakodateChan),
    KITAMI(4, "北見", "テーマパークがあります", "Kitami.png", Character.KitamiChan),
    OBIHIRO(5, "帯広", "豚丼が有名です", "Obihiro.png", Character.ObihiroChan),
    WAKKANAI(6, "稚内", "北海道最北の都市です", "Wakkanai.png", Character.WakkanaiChan);

    // DBのproducts.region_idと対応するID
    private final Integer id;

    // 画面に表示する地域名
    private final String name;

    // 地域紹介文
    private final String description;

    // 地域画像ファイル名
    private final String regionImage;

    // 地域キャラクター
    private final Character character;

    /**
     * 地域IDからRegion enumを探すメソッド
     *
     * productsテーブルから取得したregion_idを使って、該当するRegionを返す
     *
     * @param id 地域ID
     * @return 該当するRegion。見つからない場合はnull。
     */
    public static Region fromId(Integer id) {

        // idがnullの場合は探せないためnullを返す
        if (id == null) {
            return null;
        }

        // Region enumに定義されている全地域を1つずつ確認する
        for (Region region : Region.values()) {

            // enumのidと引数のidが一致したら、その地域を返す
            if (region.getId().equals(id)) {
                return region;
            }
        }

        // 一致する地域がない場合はnullを返す
        return null;
    }

    /**
     * 地域IDから地域名だけを取得するメソッド
     *
     * 画面表示ではRegionそのものではなく、札幌、小樽などの名前だけ必要なことが多いため用意している
     *
     * @param id 地域ID
     * @return 地域名。見つからない場合は「不明」。
     */
    public static String getNameById(Integer id) {

        // 地域IDからRegion enumを取得
        Region region = fromId(id);

        // Regionが見つかった場合は地域名を返す
        // 見つからなかった場合は画面でエラーにならないように「不明」を返す
        return region != null ? region.getName() : "不明";
    }
}