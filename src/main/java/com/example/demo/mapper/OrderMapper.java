package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Order;

@Mapper
public interface OrderMapper {

	void insert(Order order);
}