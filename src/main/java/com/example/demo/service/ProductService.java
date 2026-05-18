package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.mapper.ProductMapper;

@Service
public class ProductService {

	private final ProductMapper productMapper;

	public ProductService(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}

	public Product findById(int productId) {
		return productMapper.findById(productId);
	}
}