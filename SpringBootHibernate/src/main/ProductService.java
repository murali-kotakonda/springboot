package main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service(value = "pService")
public class ProductService {
	@Autowired
	@Qualifier("dao2")
	private Dao dao;
	
	public Product getArticleById(int articleId) {
		Product obj = dao.getArticleById(articleId);
		return obj;
	}	
	public List<Product> getAllArticles(){
		return dao.getAllArticles();
	}

	public  boolean addArticle(Product article){
        if (dao.articleExists(article.getTitle(), article.getCategory())) {
    	   return false;
       } else {
    	   dao.addArticle(article);
    	   return true;
       } 
		//dao.addArticle(article);
		 //return true;
	}

	public void updateArticle(Product article) {
		dao.updateArticle(article);
	}

	public void deleteArticle(int articleId) {
		dao.deleteArticle(articleId);
	}
}
