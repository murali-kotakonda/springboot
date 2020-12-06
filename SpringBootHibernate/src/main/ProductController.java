package main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class ProductController {
	@Autowired
	private ProductService articleService;
	
	//@GetMapping(value="product/{id}" , produces = "application/json")
	@RequestMapping(value="product/{id}" , produces = "application/json", 
			method = RequestMethod.GET)
	public ResponseEntity<Product> getArticleById(@PathVariable("id") Integer id) {
		Product article = articleService.getArticleById(id);
		return new ResponseEntity<Product>(article, HttpStatus.OK);
	}
	//@GetMapping(value="products" , produces = "application/json")
	@RequestMapping(value="products" , produces = "application/json")
	public ResponseEntity<List<Product>> getAllArticles() {
		List<Product> list = articleService.getAllArticles();
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}
	
	//@PostMapping(value= "product" , consumes = "application/json" ,produces = "application/json")
	@RequestMapping(value= "product" , consumes = "application/json" ,
			produces = "application/json",
			method = RequestMethod.POST)
	public ResponseEntity<Void> addArticle(@RequestBody Product article, UriComponentsBuilder builder) {
                boolean flag = articleService.addArticle(article);
                if (flag == false) {
        	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/article/{id}").buildAndExpand(article.getArticleId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	//@PutMapping(value = "product", produces = "application/json")
	@RequestMapping(value = "product", produces = "application/json",
			method = RequestMethod.PUT)
	public ResponseEntity<Product> updateArticle(@RequestBody Product article) {
		articleService.updateArticle(article);
		return new ResponseEntity<Product>(article, HttpStatus.OK);
	}
	
	//@DeleteMapping(value= "product/{id}" , produces = "application/json")
	@RequestMapping(value= "product/{id}" , produces = "application/json",
			method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer id) {
		articleService.deleteArticle(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} 