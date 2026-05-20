package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Product;
import com.example.demo.entity.RankingProduct;

@Mapper
public interface ProductMapper {

	// 全商品を取得
	List<Product> findAll();

	// 地域IDに対応する商品一覧を取得
	List<Product> findByRegionId(Integer regionId);

	// カテゴリIDに対応する商品一覧を取得
	List<Product> findByCategoryId(Integer categoryId);

	// 商品IDに対応する商品を1件取得
	Product findById(int productId);

	// カテゴリ別ランキングTOP3を取得
	List<RankingProduct> findRankingByCategoryId(Integer categoryId);

	//地域idとcategory_idの取得
	List<Product> findByRegionIdAndCategoryId(
			Integer regionId,
			Integer categoryId);

	//地域別カテゴリ別ランキング
	List<RankingProduct> findRankingByRegionIdAndCategoryId(
			Integer regionId,
			Integer categoryId);
}