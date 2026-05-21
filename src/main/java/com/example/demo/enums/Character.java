package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Character {
	// (Integer id, String name, Region region)
	SapporoChan(1, "ノエル", "sapporonoel1.png", "sapporonoel2.png", "sapporonoel3.png", Theme.WINTER),
	HakodateChan(2, "アカリ", "hakodateakari1.png", "hakodateakari2.png", "hakodateakari3.png", Theme.WINTER),
	WakkanaiChan(3, "しおり", "wakkanaisiori1.png", "wakkanaisiori2.png", "wakkanaisiori3.png", Theme.WINTER),
	KitamiChan(4, "きたみん", "kitamin1.png", "kitamin2.png", "kitamin3.png", Theme.WINTER),
	ObihiroChan(5, "ネユ", "obihironeyu1.png", "obihironeyu2.png", "obihironeyu3.png", Theme.WINTER),
	OtaruChan(6, "おたるん", "otarun1.png", "otarun2.png", "otarun3.png", Theme.WINTER);

	private Integer id;
	private String name;
	private String childImageUrl;
	private String studentImageUrl;
	private String adultImageUrl;
	private Theme theme;
}
