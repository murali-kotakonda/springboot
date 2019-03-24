package test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDAO articleDAO;

	public Product getArticleById(int articleId) {
		Product obj = articleDAO.getArticleById(articleId);
		return obj;
	}

	public List<Product> getAllArticles() {
		return articleDAO.getAllArticles();
	}

	public synchronized boolean addArticle(Product article) {
		if (articleDAO.productExists(article.getTitle(), article.getCategory())) {
			return false;
		} else {
			articleDAO.addArticle(article);
			return true;
		}
	}

	public void updateArticle(Product article) {
		articleDAO.updateProduct(article);
	}

	public void deleteArticle(int articleId) {
		articleDAO.deleteProduct(articleId);
	}
}