package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.entity.RankingProduct;
import com.example.demo.mapper.ProductMapper;

@Service
public class ProductService {

	private final ProductMapper productMapper;

	public ProductService(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}

	// 全商品を取得
	public List<Product> getAllProducts() {
		return productMapper.findAll();
	}

	// 地域IDに対応する商品一覧を取得
	public List<Product> getProductsByRegionId(Integer regionId) {
		return productMapper.findByRegionId(regionId);
	}

	// カテゴリIDに対応する商品一覧を取得
	public List<Product> getProductsByCategoryId(Integer categoryId) {
		return productMapper.findByCategoryId(categoryId);
	}

	// 商品詳細取得
	public Product findById(int productId) {
		return productMapper.findById(productId);
	}

	//フィルタリング
	public List<Product> getProductsByRegionIdAndCategoryId(
			Integer regionId,
			Integer categoryId) {

		return productMapper.findByRegionIdAndCategoryId(regionId, categoryId);
	}

	// カテゴリ別ランキングTOP3を取得
	public List<RankingProduct> getRankingByCategoryId(Integer categoryId) {
		return productMapper.findRankingByCategoryId(categoryId);
	}

	//地域別カテゴリ別ランキング
	public List<RankingProduct> getRankingByRegionIdAndCategoryId(
			Integer regionId,
			Integer categoryId) {

		return productMapper.findRankingByRegionIdAndCategoryId(regionId, categoryId);
	}

}