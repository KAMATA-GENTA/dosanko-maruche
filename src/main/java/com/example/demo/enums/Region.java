package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Region {
	//(Integer id, String name, String description) 

	Sapporo(1, "札幌", "北海道の中心都市です", "Sapporo.png", Character.SapporoChan), 
	Hakodate(2, "函館", "夜景と海鮮が有名です", "Hakodate.png",Character.HakodateChan), 
	Kitami(3,"北見", "テーマパークがあります", "Kitami.png",Character.KitamiChan), 
	Wakkanai(4, "稚内", "北海道最北の都市です", "Wakkanai.png", Character.WakkanaiChan),
	Otaru(5, "小樽", "歴史的な石造りの倉庫群が残る「小樽運河」をはじめ、ガラス工芸品、オルゴール、そして新鮮な海鮮を楽しめる観光地です", "Otaru.png",Character.OtaruChan),
	Obihiro(6, "帯広", "豚丼が有名です", "Obihiro.png", Character.ObihiroChan);

	private final Integer id;
	private final String name;
	private final String description;
	private final String regionImage;
	private final Character character;
}
