package main;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ProductDAO {
	
	@PersistenceContext	
	private EntityManager entityManager;	
	
	public Product getArticleById(int articleId) {
		return entityManager.find(Product.class, articleId);
	}

	@SuppressWarnings("unchecked")
	public List<Product> getAllArticles() {
		String hql = "FROM Article as atcl ORDER BY atcl.articleId";
		return (List<Product>) entityManager.createQuery(hql).getResultList();
	}	

	public void addArticle(Product article) {
		entityManager.persist(article);
	}

	public void updateArticle(Product article) {
		Product artcl = getArticleById(article.getArticleId());
		artcl.setTitle(article.getTitle());
		artcl.setCategory(article.getCategory());
		entityManager.flush();
	}

	public void deleteArticle(int articleId) {
		entityManager.remove(getArticleById(articleId));
	}

	public boolean articleExists(String title, String category) {
		String hql = "FROM Article as atcl WHERE atcl.title = ? and atcl.category = ?";
		int count = entityManager.createQuery(hql).setParameter(1, title)
		              .setParameter(2, category).getResultList().size();
		return count > 0 ? true : false;
	}
}
