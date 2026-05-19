package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Product;

@Mapper
public interface ProductMapper {

	@Select("""
			SELECT
			    id,
			    region_id AS regionId,
			    category_id AS categoryId,
			    product_name AS name,
			    price,
			    description,
			    image_url AS imageUrl
			FROM products
			""")
	List<Product> findAll();

	@Select("""
			SELECT
			    id,
			    region_id AS regionId,
			    category_id AS categoryId,
			    product_name AS name,
			    price,
			    description,
			    image_url AS imageUrl
			FROM products
			WHERE region_id = #{regionId}
			""")
	List<Product> findByRegionId(Integer regionId);

	@Select("""
			SELECT
			    id,
			    region_id AS regionId,
			    category_id AS categoryId,
			    product_name AS name,
			    price,
			    description,
			    image_url AS imageUrl
			FROM products
			WHERE category_id = #{categoryId}
			""")
	List<Product> findByCategoryId(Integer categoryId);

	// 商品IDに対応する商品を1件取得
	Product findById(int productId);
}