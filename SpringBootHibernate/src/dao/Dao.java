package dao;

import java.util.List;

import main.Product;

public interface Dao {
	public Product getArticleById(int articleId) ;
	public List<Product> getAllArticles() ;
	public Product addArticle(Product article);
	public void updateArticle(Product article);
	public void deleteArticle(int articleId) ;
	public boolean articleExists(String title, String category);
}
