package main;

import java.util.List;

public interface Dao {
	public Product getArticleById(int articleId) ;
	public List<Product> getAllArticles() ;
	public void addArticle(Product article);
	public void updateArticle(Product article);
	public void deleteArticle(int articleId) ;
	public boolean articleExists(String title, String category);
}
