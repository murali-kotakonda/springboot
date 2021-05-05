package com.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductService {
	
	@Autowired
	IntegrationService service;

	public List<Product> getProducts() {
		return service.getProductList();
	}

	public void add(Product product) {
		service.createProduct(product);
	}

	public Product getProduct(Product pdt) {
		return service.getProductById(""+pdt.getArticleId());
	}

	public boolean deleteProduct(Product pdt) {
		String msg = service.deleteProduct(""+pdt.getArticleId());
		return (msg!=null);
	}

	public Product updateProduct(Product product) {
		String res = service.updateProduct(product);
		return product;
	}
}
