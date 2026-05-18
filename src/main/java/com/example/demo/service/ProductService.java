package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<ProductEntity> getAllProducts() {
		return productRepository.findAll();
	}

	public List<ProductEntity> getProductsByRegionId(Integer regionId) {
		return productRepository.findByRegionId(regionId);
	}

	public List<ProductEntity> getProductsByCategoryId(Integer categoryId) {
		return productRepository.findByCategoryId(categoryId);
	}
}