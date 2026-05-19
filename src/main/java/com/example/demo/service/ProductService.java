package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.mapper.ProductMapper;

@Service
public class ProductService {

	private final ProductMapper productMapper;

	public ProductService(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}

	// 全商品取得
	public List<Product> getAllProducts() {
		return productMapper.findAll();
	}

	// 地域検索
	public List<Product> getProductsByRegionId(Integer regionId) {
		return productMapper.findByRegionId(regionId);
	}

	// カテゴリ検索
	public List<Product> getProductsByCategoryId(Integer categoryId) {
		return productMapper.findByCategoryId(categoryId);
	}

	// 商品詳細取得
	public Product findById(int productId) {
		return productMapper.findById(productId);
	}
}