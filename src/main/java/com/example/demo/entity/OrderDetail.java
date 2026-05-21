package com.example.demo.entity;

import lombok.Data;

@Data
public class OrderDetail {
	private Integer id;
	private Integer orderId;
	private Integer productId;
	private Integer quantity;
	private Integer price;
	private String name;
}
