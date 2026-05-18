package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "region_id")
	private Integer regionId;

	@Column(name = "category_id")
	private Integer categoryId;

	@Column(name = "product_name")
	private String productName;

	private Integer price;

	private String description;

	@Column(name = "image_url")
	private String imageUrl;

	public Integer getId() {
		return id;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public String getProductName() {
		return productName;
	}

	public Integer getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
