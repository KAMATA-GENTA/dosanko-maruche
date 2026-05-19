package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.RegionCount;
import com.example.demo.entity.User;

@Mapper
public interface UserMapper {

	/** メールアドレスでユーザを検索する */
	User findByEmail(String email);

	/** ユーザを登録する */
	void insert(User user);

	User findById(Integer id);

	List<Order> findOrdersByUserId(Integer userId);

	List<RegionCount> findRegionCountByUserId(Integer userId);

	List<OrderDetail> findOrderDetailsByOrderId(Integer orderId);
}