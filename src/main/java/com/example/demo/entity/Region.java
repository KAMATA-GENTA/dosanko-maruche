package com.example.demo.entity;

import lombok.Data;

@Data
public class Region {

	private Integer id;

	/** 地域名 */
	private String name;

	/** 地域説明 */
	private String description;

	public Region(Integer id) {
		this.id = id;
		switch ((int) id) {
		case 1: {
			this.name = "札幌";
			this.description = "北海道の中心都市です";
			break;
		}
		case 2: {
			this.name = "函館";
			this.description = "夜景と海鮮が有名です。";
			break;
		}
		case 3: {
			this.name = "稚内";
			this.description = "北海道の最北に位置する都市です";
			break;
		}
		case 4: {
			this.name = "北見";
			this.description = "テーマパークがあります";
			break;
		}
		case 5: {
			this.name = "帯広";
			this.description = "豚丼が有名です";
			break;
		}
		}
	}
}

//package com.example.demo.entity;
//
//import lombok.Data;
//
//@Data
//public class Region {
//
//	/** 地域ID */
//	private Integer id;
//
//	/** 地域説明 */
//	private String description;
//
//	public Region(Integer id,String description) {
//		this.id = id;
//		this.description = description;
//		//id=1ならthis.descriptionにid=1の地域の説明を代入する
//		//id=2ならthis.descriptionにid=2の地域の説明を代入する
//		String sapporo = 
//		if(id == 1) {
//			description = 
//		}
//
//	}
//
//}