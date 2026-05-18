package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ProductEntity;
import com.example.demo.mapper.ProductMapper;

@Service
public class ProductService {

	private final ProductMapper productMapper;

	// コンストラクタ Springが自動でProductMapperを渡してくれる
	public ProductService(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}

	// 全商品を取得
	public List<ProductEntity> getAllProducts() {
		return productMapper.findAll();
	}

	// 地域IDに対応する商品一覧を取得
	public List<ProductEntity> getProductsByRegionId(Integer regionId) {
		return productMapper.findByRegionId(regionId);
	}

	// カテゴリIDに対応する商品一覧を取得
	public List<ProductEntity> getProductsByCategoryId(Integer categoryId) {
		return productMapper.findByCategoryId(categoryId);
	}
}