package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

	 // 全商品を取得
    public List<Product> getAllProducts() {
        List<Product> products = productMapper.findAll();
        setDisplayNames(products);
        return products;
    }

    // 地域IDに対応する商品一覧を取得
    public List<Product> getProductsByRegionId(Integer regionId) {
        List<Product> products = productMapper.findByRegionId(regionId);
        setDisplayNames(products);
        return products;
    }

    // カテゴリIDに対応する商品一覧を取得
    public List<Product> getProductsByCategoryId(Integer categoryId) {
        List<Product> products = productMapper.findByCategoryId(categoryId);
        setDisplayNames(products);
        return products;
    }

    // 商品詳細取得
    public Product findById(int productId) {
        Product product = productMapper.findById(productId);

        // 商品が存在するときだけ、enumから表示名をセットする
        if (product != null) {
            setDisplayName(product);
        }

        return product;
    }

    // カテゴリ別ランキングTOP3を取得
    public List<RankingProduct> getRankingByCategoryId(Integer categoryId) {
        List<RankingProduct> rankingProducts = productMapper.findRankingByCategoryId(categoryId);
        setRankingDisplayNames(rankingProducts);
        return rankingProducts;
    }

    // 商品一覧に地域名・カテゴリ名をセットする
    private void setDisplayNames(List<Product> products) {
        if (products == null) {
            return;
        }

        for (Product product : products) {
            setDisplayName(product);
        }
    }

    // 1商品に地域名・カテゴリ名をセットする
    private void setDisplayName(Product product) {
        if (product == null) {
            return;
        }

        // regionIdからRegion enumを探して地域名をセットする
        product.setRegionName(Region.getNameById(product.getRegionId()));

        // categoryIdからCategory enumを探してカテゴリ名をセットする
        product.setCategoryName(Category.getNameById(product.getCategoryId()));
    }

    // ランキング商品に地域名をセットする
    private void setRankingDisplayNames(List<RankingProduct> rankingProducts) {
        if (rankingProducts == null) {
            return;
        }

        for (RankingProduct rankingProduct : rankingProducts) {
            rankingProduct.setRegionName(Region.getNameById(rankingProduct.getRegionId()));
        }
    }
}