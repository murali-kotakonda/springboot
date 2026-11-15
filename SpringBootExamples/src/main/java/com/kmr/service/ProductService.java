package com.kmr.service;

import java.util.List;

import com.kmr.dto.Product;

public interface ProductService {

	Product createProduct(Product product);

	Product getProductById(String id);

	List<Product> getAllProducts();

	Product updateProduct(String id, Product product);

	void deleteProduct(String id);
}
