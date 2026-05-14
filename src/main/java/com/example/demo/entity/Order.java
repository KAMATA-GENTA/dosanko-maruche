package com.example.demo.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Order {

	private Integer id;
	private Integer userId;
	private Integer subtotal;
	private Integer shippingFee;
	private LocalDateTime orderedAt;
}
