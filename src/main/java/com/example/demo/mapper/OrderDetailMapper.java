package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.OrderDetail;

@Mapper
public interface OrderDetailMapper {

	@Select("""
			SELECT COUNT(od.id)
			FROM order_details od
			INNER JOIN products p ON od.product_id = p.id
			WHERE p.region_id = #{regionId}
			""")
	int countByRegionId(@Param("regionId") Integer regionId);

	void insert(OrderDetail orderDetail);
}