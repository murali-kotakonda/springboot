package com.kmr.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kmr.dto.Product;

@Service
public class ProductServiceImpl implements ProductService {

	private static Map<String, Product> productRepo = new HashMap<>();

	@Override
	public Product createProduct(Product product) {
		productRepo.put(product.getId(), product);
		return product;
	}

	@Override
	public Product getProductById(String id) {
		return productRepo.get(id);
	}

	@Override
	public List<Product> getAllProducts() {
		return new ArrayList<>(productRepo.values());
	}

	@Override
	public Product updateProduct(String id, Product product) {
		product.setId(id);
		productRepo.put(id, product);
		return product;
	}

	@Override
	public void deleteProduct(String id) {
		productRepo.remove(id);
	}
}
