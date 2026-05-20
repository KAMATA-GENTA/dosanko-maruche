package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Region {
	//(Integer id, String name, String description) 

	Sapporo("札幌", "北海道の中心都市です", "Sapporo.png", Character.SapporoChan, 1, "#dff5ff"), Hakodate("函館", "夜景と海鮮が有名です",
			"Hakodate.png", Character.HakodateChan, 2,
			"#1b2b52"), Wakkanai("稚内", "北海道最北の都市です", "Wakkanai.png", Character.WakkanaiChan, 4, "#d9f2ff"), Kitami("北見",
					"テーマパークがあります", "Kitami.png",
					Character.KitamiChan, 3,
					"#fff1cc"), Obihiro("帯広", "豚丼が有名です", "Obihiro.png", Character.ObihiroChan, 6, "#ffe0cc"), Otaru(
							"小樽", "歴史的な石造りの倉庫群が残る「小樽運河」をはじめ、ガラス工芸品、オルゴール、そして新鮮な海鮮を楽しめる観光地です", "Otaru.png",
							Character.OtaruChan, 5, "#d6f0ff");

	private final String name;
	private final String description;
	private final String regionImage;
	private final Character charaImage;
	private final Integer region_id;
	private final String backgroundColor;

}
