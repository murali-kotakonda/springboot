package test;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClient {
	    public void getArticleByIdDemo() {
	    	HttpHeaders headers = new HttpHeaders();
	    	headers.setContentType(MediaType.APPLICATION_JSON);
	        RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/user/article/{id}";
	        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
	        ResponseEntity<Product> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Product.class, 1);
	        Product article = responseEntity.getBody();
	        System.out.println("Id:"+article.getArticleId()+", Title:"+article.getTitle()
	                 +", Category:"+article.getCategory());      
	    }
	    public void getAllArticlesDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	        RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/user/articles";
	        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
	        ResponseEntity<Product[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Product[].class);
	        Product[] articles = responseEntity.getBody();
	        for(Product article : articles) {
	              System.out.println("Id:"+article.getArticleId()+", Title:"+article.getTitle()
	                      +", Category: "+article.getCategory());
	        }
	    }
	    public void addArticleDemo() {
	    	HttpHeaders headers = new HttpHeaders();
	    	headers.setContentType(MediaType.APPLICATION_JSON);
	        RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/user/article";
		Product objArticle = new Product();
		objArticle.setTitle("Spring REST Security using Hibernate");
		objArticle.setCategory("Spring");
	        HttpEntity<Product> requestEntity = new HttpEntity<Product>(objArticle, headers);
	        URI uri = restTemplate.postForLocation(url, requestEntity);
	        System.out.println(uri.getPath());    	
	    }
	    public void updateArticleDemo() {
	    	HttpHeaders headers = new HttpHeaders();
	    	headers.setContentType(MediaType.APPLICATION_JSON);
	        RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/user/article";
		Product objArticle = new Product();
		objArticle.setArticleId(1);
		objArticle.setTitle("Update:Java Concurrency");
		objArticle.setCategory("Java");
	        HttpEntity<Product> requestEntity = new HttpEntity<Product>(objArticle, headers);
	        restTemplate.put(url, requestEntity);
	    }
	    public void deleteArticleDemo() {
	    	HttpHeaders headers = new HttpHeaders();
	    	headers.setContentType(MediaType.APPLICATION_JSON);
	        RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/user/article/{id}";
	        HttpEntity<Product> requestEntity = new HttpEntity<Product>(headers);
	        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 1);        
	    }
	    public static void main(String args[]) {
	    	RestClient util = new RestClient();
	        //util.getArticleByIdDemo();
	    	//util.addArticleDemo();
	    	//util.updateArticleDemo();
	    	//util.deleteArticleDemo();
	    	util.getAllArticlesDemo();    	
	    }    
	} 
