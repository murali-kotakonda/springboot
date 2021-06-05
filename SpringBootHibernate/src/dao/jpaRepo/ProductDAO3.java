package dao.jpaRepo;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.Dao;
import main.Product;
import service.ServiceException;

@Transactional
@Repository(value = "dao3")
public class ProductDAO3 implements Dao{
	
	@Autowired
	ProductJPARepository productRepository;
	
	public Product getArticleById(int articleId) {
		Optional<Product> res = productRepository.findById(articleId);
		//productRepository.findById(articleId).orElse(null);
		return res.get();
	}

	@SuppressWarnings("unchecked")
	public List<Product> getAllArticles() {
		return productRepository.findAll();
	}	

	public Product addArticle(Product article) {
		Product product = productRepository.save(article);
		return product;
	}

	public void updateArticle(Product article) {
		Product artcl = getArticleById(article.getArticleId());
		if(artcl==null) {
			throw new ServiceException("Invalid product id","");
		}
		artcl.setTitle(article.getTitle());
		artcl.setCategory(article.getCategory());
		productRepository.flush();
	}

	public void deleteArticle(int articleId) {
		Product artcl = getArticleById(articleId);
		if(artcl==null) {
			throw new ServiceException("Invalid product id","");
		}
		productRepository.delete(getArticleById(articleId));
	}

	public boolean articleExists(String title, String category) {
		return productRepository.existsByTitleAndCategory(title,category);
	}
}
