package com.kmr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kmr.dto.Product;
import com.kmr.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController2 {

	@Autowired
	private ProductService productService;

	// 1. Create Product
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
	}

	// 2. Get Product by ID
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable String id) {
		return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
	}

	// 3. Get All Products
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	}

	// 4. Update Product by ID
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
		return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
	}

	// 5. Delete Product by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable String id) {
		productService.deleteProduct(id);
		Map<String, String> response = new HashMap<>();
		response.put("message", "Product deleted successfully");
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	}
}
