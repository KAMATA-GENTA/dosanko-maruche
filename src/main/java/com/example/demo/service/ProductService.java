package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.mapper.ProductMapper;

@Service
public class ProductService {

	private final ProductMapper productMapper;

	// コンストラクタ Springが自動でProductMapperを渡してくれる
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
}