package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dao.Dao;
import main.Product;
@Service(value = "pService")
public class ProductService {
	//@Autowired
	@Qualifier("dao3")
	private Dao dao;
	
	public Product getArticleById(int articleId) {
		Product obj = dao.getProductById(articleId);
		return obj;
	}	
	public List<Product> getAllArticles(){
		return dao.getAllProducts();
	}

	public  boolean addArticle(Product article){
        if (dao.productExists(article.getTitle(), article.getCategory())) {
    	   return false;
       } else {
    	   dao.addProduct(article);
    	   return true;
       } 
		//dao.addArticle(article);
		 //return true;
	}

	public void updateArticle(Product article) {
		dao.updateProduct(article);
	}

	public void deleteArticle(int articleId) {
		dao.deleteArticle(articleId);
	}
}
