package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Product;

@Mapper
public interface ProductMapper {
	Product findById(int productId);
}
