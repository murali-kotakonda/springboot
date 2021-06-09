package app.daoJpa;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.dao.Dao;
import app.util.Product;
import app.util.ServiceException;

@Transactional
@Repository(value = "dao3")
public class ProductDAO3 implements Dao{
	
	@Autowired
	ProductJPARepository productRepository;
	
	public Product getProductById(int articleId) {
		Optional<Product> res = productRepository.findById(articleId);
		//productRepository.findById(articleId).orElse(null);
		return res.get();
	}

	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}	

	public Product addProduct(Product article) {
		Product product = productRepository.save(article);
		return product;
	}

	public void updateProduct(Product article) {
		Product artcl = getProductById(article.getArticleId());
		if(artcl==null) {
			throw new ServiceException("Invalid product id","");
		}
		artcl.setTitle(article.getTitle());
		artcl.setCategory(article.getCategory());
		productRepository.flush();
	}

	public boolean deleteProduct(int articleId) {
		Product artcl = getProductById(articleId);
		if(artcl==null) {
			throw new ServiceException("Invalid product id","");
		}
		productRepository.delete(getProductById(articleId));
		return true;
	}

	public boolean productExists(String title, String category) {
		return productRepository.existsByTitleAndCategory(title,category);
	}
}
