package com.dailycodework.dream_shops.service.product;

import com.dailycodework.dream_shops.Model.Product;
import com.dailycodework.dream_shops.request.AddProductRequest;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    void updateProduct(Long productId,Product product);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brandId);
    List<Product> getProductsByCategoryAndBrand(String category,String brand);
    List<Product> getProductsByName(String name);

    List<Product> getProductsByBrandAndName(String category,String name);

    Long countProductsByBrandAndName(String brand,String name);

}
