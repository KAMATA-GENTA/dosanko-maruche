package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.OrderDetail;

@Mapper
public interface OrderDetailMapper {

	void insert(OrderDetail orderDetail);
}