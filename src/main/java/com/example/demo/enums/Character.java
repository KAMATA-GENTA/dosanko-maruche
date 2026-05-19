package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Character {
	//(Integer id, String name, Region region)
	SapporoChan(1, "ノエル", "sapporonoel1.png", "sapporonoel2.png", "sapporonoel3.png"), 
	HakodateChan(2, "アカリ","hakodateakari1.png", "hakodateakari2.png", "hakodateakari3.png"), 
	WakkanaiChan(3, "しおり", "wakkanaisiori1.png","wakkanaisiori2.png", "wakkanaisiori3.png"),
	KitamiChan(4, "きたみん", "kitamin1.png", "kitamin2.png","kitamin3.png"), 
	ObihiroChan(5, "ネユ", "obihiro1.png", "obihiro2.png","obihiro3.png"),
	OtaruChan(6, "おたるん", "otarun1.png", "otarun2.png", "otarun3.png");

	private Integer id;
	private String name;
	private String childImageUrl;
	private String studentImageUrl;
	private String adultImageUrl;
}
