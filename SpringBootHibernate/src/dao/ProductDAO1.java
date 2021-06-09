package dao;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import main.Product;

@Transactional
@Repository(value = "dao1")
public class ProductDAO1 implements Dao{
	
	@Autowired
	ProductCrudRepository productRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public Product getArticleById(int articleId) {
		return productRepository.findById(articleId).get();
	}

	@SuppressWarnings("unchecked")
	public List<Product> getAllArticles() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(product -> products.add(product));
		return products;
	}	

	public Product addArticle(Product article) {
		Product product = productRepository.save(article);
		return product;
	}

	public void updateArticle(Product article) {
		Product artcl = getArticleById(article.getArticleId());
		artcl.setTitle(article.getTitle());
		artcl.setCategory(article.getCategory());
		productRepository.save(artcl);
	}

	public boolean deleteArticle(int articleId) {
		productRepository.deleteById(articleId);
		return true;
	}

	public boolean articleExists(String title, String category) {
		String hql = "FROM Product as atcl WHERE atcl.title = :title and atcl.category = :category";
		int count = entityManager.createQuery(hql).setParameter("title", title)
		              .setParameter("category", category).getResultList().size();
		return count > 0 ? true : false;
	}
}
