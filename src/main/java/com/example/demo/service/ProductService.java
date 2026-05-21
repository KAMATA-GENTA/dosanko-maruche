package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductWithCategoryWithRegion;
import com.example.demo.entity.Product;
import com.example.demo.entity.RankingProduct;
import com.example.demo.enums.Category;
import com.example.demo.enums.Region;
import com.example.demo.mapper.ProductMapper;

@Service
public class ProductService {

	private final ProductMapper productMapper;

	public ProductService(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}

	/**
	 * 全商品を取得します。
	 *
	 * Product entity はDBと同じ構造にするため、 regionName / categoryName のような表示用項目はセットしません。
	 *
	 * 地域名・カテゴリ名を画面に表示したい場合は、 HTML側で product.regionId / product.categoryId から enum
	 * を使って表示します。
	 */
	public List<Product> getAllProducts() {
		return productMapper.findAll();
	}

	/**
	 * 地域IDに対応する商品一覧を取得します。
	 *
	 * DBの products.region_id に対応する値で検索します。
	 */
	public List<Product> getProductsByRegionId(Integer regionId) {
		return productMapper.findByRegionId(regionId);
	}

	/**
	 * カテゴリIDに対応する商品一覧を取得します。
	 *
	 * DBの products.category_id に対応する値で検索します。
	 */
	public List<Product> getProductsByCategoryId(Integer categoryId) {
		return productMapper.findByCategoryId(categoryId);
	}

	/**
	 * 商品詳細を1件取得します。
	 *
	 * Product entity に表示用の地域名・カテゴリ名は持たせないため、 ここでも表示名のセットは行いません。
	 */
	public Product findById(int productId) {
		return productMapper.findById(productId);
	}

	/**
	 * カテゴリ別ランキングTOP3を取得します。
	 *
	 * RankingProduct はランキング表示用のentityとして使っているため、 ランキングカードで表示する地域名だけ enum からセットします。
	 */
	public List<RankingProduct> getRankingByCategoryId(Integer categoryId) {
		List<RankingProduct> rankingProducts = productMapper.findRankingByCategoryId(categoryId);

		setRankingDisplayNames(rankingProducts);

		return rankingProducts;
	}

	/**
	 * 地域別・カテゴリ別ランキングTOP3を取得します。
	 *
	 * 地域ページで「海産物ランキング」「農産物ランキング」などを表示するために使います。
	 */
	public List<RankingProduct> getRankingByRegionIdAndCategoryId(Integer regionId, Integer categoryId) {

		List<RankingProduct> rankingProducts = productMapper.findRankingByRegionIdAndCategoryId(regionId, categoryId);

		setRankingDisplayNames(rankingProducts);

		return rankingProducts;
	}

	/**
	 * ランキング商品に表示用の地域名をセットします。
	 *
	 * 通常のProduct entityには表示用フィールドを追加しませんが、 RankingProduct はランキング表示専用として使っているため、
	 * regionId から地域名を補完しています。
	 */
	private void setRankingDisplayNames(List<RankingProduct> rankingProducts) {
		if (rankingProducts == null) {
			return;
		}

		for (RankingProduct rankingProduct : rankingProducts) {
			rankingProduct.setRegionName(Region.getNameById(rankingProduct.getRegionId()));
		}
	}

	public List<ProductWithCategoryWithRegion> findAllWithCategoryWithRegions(List<Product> products) {

		return products.stream().map(this::convert).toList();
	}

	private ProductWithCategoryWithRegion convert(Product product) {

		ProductWithCategoryWithRegion dto = new ProductWithCategoryWithRegion();

		// product情報
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setDescription(product.getDescription());
		dto.setPrice(product.getPrice());
		dto.setImageUrl(product.getImageUrl());

		// category（enum結合）
		Category category = Category.fromId(product.getCategoryId());
		if (category != null) {
			dto.setCategoryId(category.getId());
			dto.setCategoryName(category.getName());
		}

		// region（enum結合）
		Region region = Region.fromId(product.getRegionId());
		if (region != null) {
			dto.setRegionId(region.getId());
			dto.setRegionName(region.getName());
		}

		return dto;
	}
}