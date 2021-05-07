package demo.student;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
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
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("v1/products")
public class ProductController {

    private static  List<Product> PRODUCTS = new ArrayList<>();
    static {
    	PRODUCTS.add(new Product(1, "Samsung"));
    	PRODUCTS.add(new Product(2, "Apple"));
    	PRODUCTS.add(new Product(3, "Motorola"));
    }
    
    @GetMapping
 	public ResponseEntity<ResponseInfo<Product>> getAllArticles() {
 		List<Product> list = PRODUCTS;
 		return new ResponseEntity(new ResponseInfo<Product>("success", list), HttpStatus.OK);
 	}

 	@GetMapping(value = "/{id}")
 	public ResponseEntity<Product> getArticleById(@PathVariable("id") Integer productId) {
 		Product article = PRODUCTS.stream()
                .filter(product -> productId.equals(product.getProductId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "productId " + productId + " does not exists"
                ));
 		return new ResponseEntity<Product>(article, HttpStatus.OK);
 	}
 	
 	@DeleteMapping(value = "/{id}")
 	public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer productId ) {
 		PRODUCTS.removeIf(product -> productId.equals(product.getProductId()));
 		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
 	}
 		
 	@PostMapping(consumes = "application/json" ,produces = "application/json")
 	public ResponseEntity<Void> addArticle(@RequestBody Product article, UriComponentsBuilder builder) {
 		Integer productId = article.getProductId();        
 		boolean exists = PRODUCTS.stream()
                         .filter(product -> productId.equals(product.getProductId()))
                         .findFirst()
                         .orElse(null)!=null;
                 if (exists) {
                 	return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
                 }
                 PRODUCTS.add(article);
                 HttpHeaders headers = new HttpHeaders();
                 headers.setLocation(builder.path("/article/{id}").buildAndExpand(article.getProductId()).toUri());
                 return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
 	}
 	
 	@PutMapping( produces = "application/json",consumes = "application/json")
 	public ResponseEntity<Product> updateArticle(@RequestBody Product article) {
 		Integer productId = article.getProductId();    
 		Product pro = PRODUCTS.stream()
                .filter(product -> productId.equals(product.getProductId()))
                .findFirst()
                .orElse(null);
        if (pro==null) {
        	return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
        }
        pro.setProductName(article.getProductName());
 		return new ResponseEntity<Product>(article, HttpStatus.OK);
 	}
}
