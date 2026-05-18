package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Character {
	//(Integer id, String name, Region region)
	SapporoChan("ノエル", "sapporonoel1.png", "sapporonoel2.png", "sapporonoel3.png"), HakodateChan("アカリ",
			"hakodateakari1.png", "hakodateakari2.png", "hakodateakari3.png"), WakkanaiChan("しおり", "wakkanaisiori1.png",
					"wakkanaisiori2.png", "wakkanaisiori3.png"), KitamiChan("きたみん", "kitamin1.png", "kitamin2.png",
							"kitamin3.png"), ObihiroChan("ネユ", "obihiro1.png", "obihiro2.png",
									"obihiro3.png"), OtaruChan("おたるん", "otarun1.png", "otarun2.png", "otarun3.png");

	private String name;
	private String charaImage;
	private String charaImage2;
	private String cahaImage3;

}
