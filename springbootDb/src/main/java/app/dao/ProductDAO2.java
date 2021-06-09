package app.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.util.Product;
import app.util.ServiceException;

@Transactional
@Repository(value = "dao2")
public class ProductDAO2 implements Dao{
	
	@PersistenceContext	
	private EntityManager entityManager;	
	
	public Product getProductById(int articleId) {
		return entityManager.find(Product.class, articleId);
	}

	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts() {
		String hql = "FROM Product as atcl ORDER BY atcl.articleId";
		return (List<Product>) entityManager.createQuery(hql).getResultList();
	}	

	public Product addProduct(Product article) {
		Product product = entityManager.merge(article);
		return product;
	}

	public void updateProduct(Product article) {
		Product artcl = getProductById(article.getArticleId());
		if(artcl==null) {
			throw new ServiceException("Invalid product id","");
		}
		artcl.setTitle(article.getTitle());
		artcl.setCategory(article.getCategory());
		entityManager.flush();
	}

	public boolean deleteProduct(int articleId) {
		Product artcl = getProductById(articleId);
		if(artcl==null) {
			throw new ServiceException("Invalid product id","");
		}
		entityManager.remove(getProductById(articleId));
		return true;
	}

	public boolean productExists(String title, String category) {
		String hql = "FROM Product as atcl WHERE atcl.title = :title and atcl.category = :category";
		int count = entityManager.createQuery(hql).setParameter("title", title)
		              .setParameter("category", category).getResultList().size();
		return count > 0 ? true : false;
	}

}
