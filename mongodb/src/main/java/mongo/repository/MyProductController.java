package mongo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mongo.model.Product;
import mongo.model.ProductResponse;

@RestController
public class MyProductController {

	@Autowired
	private ProductRepository repository;

	@PostMapping("/product")
	public String saveProduct(@RequestBody Product product) {
		repository.save(product);
		return "Added Product with id : " + product.getArticleId();
	}

	@PutMapping("/product")
	public String updateProduct(@RequestBody Product product) {
		repository.save(product);
		return "updated Product with id : " + product.getArticleId();
	}

	@GetMapping("/products")
	public List<Product> getProducts() {
		return repository.findAll();
	}

	@GetMapping("/product/{id}")
	public ProductResponse getProduct(@PathVariable String id) {
		Optional<Product> product = repository.findById(id);
		ProductResponse response = new ProductResponse();
		if(product.isPresent()) {
			response.setMessage("success");
			response.getProducts().add(product.get());
		}else{
			response.setMessage("product not found");
		}
		return response;
	}
	
	@GetMapping("/product/article/{id}")
	public ProductResponse getProductByArticle(@PathVariable int id) {
		Product product = repository.findProductsByArticleId(id);
		ProductResponse response = new ProductResponse();
		if(product !=null) {
			response.setMessage("success");
			response.getProducts().add(product);
		}else{
			response.setMessage("product not found");
		}
		return response;
	}
	
	@GetMapping("/product/category/{category}")
	public ProductResponse getProductByArticle(@PathVariable String category) {
		List<Product> products = repository.findByCategory(category);
		ProductResponse response = new ProductResponse();
		if(products !=null) {
			response.setMessage("success");
			response.getProducts().addAll(products);
		}else{
			response.setMessage("products not found");
		}
		return response;
	}

	@DeleteMapping("/product/article/{id}")
	public ProductResponse deleteProductByArticle(@PathVariable int id) {
		Long count = repository.deleteByArticleId(id);
		ProductResponse response = new ProductResponse();
		if(count !=null  && count!=0) {
			response.setMessage("deleted");
		}else{
			response.setMessage("product not found");
		}
		return response;
	}
	
	@DeleteMapping("/product/{id}")
	public String deleteProduct(@PathVariable String id) {
		repository.deleteById(id);
		return "Product deleted with id : " + id;
	}
}
