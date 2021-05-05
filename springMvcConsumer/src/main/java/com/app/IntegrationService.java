package com.app;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class IntegrationService {

	@Autowired
	RestTemplate restTemplate;

	private static final String URL = "http://localhost:8079";

	public List<Product> getProductList() {

		// prepare headers
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=");
		HttpEntity<String> entity = new HttpEntity<>(headers);

		String url = URL + "/products";

		// call exchange method()
		// by passing url + httpmrthod + body + headers + tranformation class

		// json is converted to ProductResponseInfo object
		ResponseEntity<ProductResponseInfo> response = restTemplate.exchange(url, HttpMethod.GET, entity,
				ProductResponseInfo.class);
		return response.getBody().getProducts();

	}


	public String getProductsJson() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=");

		HttpEntity<String> entity = new HttpEntity<>(headers);
		return restTemplate.exchange(URL + "/products", HttpMethod.GET, entity, String.class).getBody();
	}

		
		//-----------------------------------------------------------------------------
		
	public Product getProductById(String id) {
		// prepare headers
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=");
		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<Product> response = restTemplate.exchange(URL + "/product/" + id, HttpMethod.GET, entity,
				Product.class);
		HttpStatus statusCode = response.getStatusCode();
		Product p = response.getBody();
		return p;
	}
		  //--------------------------------------
	public String deleteProduct(String id) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=");
		HttpEntity<Product> entity = new HttpEntity<Product>(headers);

		ResponseEntity<String> response = restTemplate.exchange(URL + "/product/" + id, HttpMethod.DELETE, entity,
				String.class);
		HttpStatus statusCode = response.getStatusCode();
		return response.getBody();
	}
         
		//provide conten type if there is a request json
		//product object is converted to request json

	public String createProduct(Product product) {
		HttpHeaders headers = updateHeaders();
		HttpEntity<Product> entity = new HttpEntity<>(product, headers);

		ResponseEntity<String> response = restTemplate.exchange(URL + "/product", HttpMethod.POST, entity,
				String.class);
		return response.getBody();
	}
		
	public String updateProduct(Product product) {
		HttpHeaders headers = updateHeaders();
		HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL + "/product", HttpMethod.PUT, entity, String.class);
		HttpStatus statusCode = response.getStatusCode();
		return response.getBody();
	}

	private HttpHeaders updateHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=");
		return headers;
	}

  }
