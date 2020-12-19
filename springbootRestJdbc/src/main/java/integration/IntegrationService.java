package integration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import util.Product;

@Component
public class IntegrationService {
	
	@Autowired
	RestTemplate restTemplate;

	public String getProductList() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.exchange("http://localhost:8080/products", HttpMethod.GET, entity, String.class).getBody();
	}
	
	public String getProductById(String id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/product/"+id, HttpMethod.GET, entity, String.class);
		HttpStatus statusCode = response.getStatusCode();
		if(statusCode == HttpStatus.OK) {
			return response.getBody();
		}
		return "Invalid product";
	}

	public String createProducts(Product product) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);
		return restTemplate.exchange("http://localhost:8080/products", HttpMethod.POST, entity, String.class).getBody();
	}

	public String updateProduct(String id, Product product) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);

		return restTemplate.exchange("http://localhost:8080/products/" + id, HttpMethod.PUT, entity, String.class)
				.getBody();
	}

	public String deleteProduct(String id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Product> entity = new HttpEntity<Product>(headers);
		return restTemplate.exchange("http://localhost:8080/products/" + id, HttpMethod.DELETE, entity, String.class)
				.getBody();
	}
}
