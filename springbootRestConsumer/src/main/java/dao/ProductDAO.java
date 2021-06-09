package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import util.Product;
import util.ServiceException;

/**
  create table :
  name : Product
  
  columns:
  articleId  - number
  title  - varchar2
  category -varchar2

 provide the db configuration in application.properties

 */
@Transactional
@Repository
public class ProductDAO implements Dao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/* class ArticleRowMapper implements RowMapper<Product> {
		@Override
		public Product mapRow(ResultSet row, int rowNum) throws SQLException {
			Product article = new Product();
			article.setArticleId(row.getInt("articleId"));
			article.setTitle(row.getString("title"));
			article.setCategory(row.getString("category"));
			return article;
		}
	} 
	RowMapper<Product> rowMapper = new ArticleRowMapper();
	*/
	
	RowMapper<Product> rowMapper = new RowMapper<Product>() {
		@Override
		public Product mapRow(ResultSet row, int rowNum) throws SQLException {
			Product article = new Product();
			article.setArticleId(row.getInt("articleId"));
			article.setTitle(row.getString("title"));
			article.setCategory(row.getString("category"));
			return article;
		}
	};
			
	public Product getProductById(int articleId) {
		String sql = "SELECT articleId, title, category FROM Product WHERE articleId = ?";
		try {
			Product article = jdbcTemplate.queryForObject(sql, rowMapper, articleId);
			return article;
		}catch(Exception ex) {
			throw new ServiceException("invalid Product ID.", "err03");
		}
	}

	// RowMapper<Article> rowMapper = new
			// BeanPropertyRowMapper<Article>(Article.class);
	public List<Product> getAllProducts() {
		String sql = "SELECT articleId, title, category FROM Product";
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	public Product addProduct(Product product){
		//Add article
		String sql = "INSERT INTO Product (articleId, title, category) values (?, ?, ?)";
		jdbcTemplate.update(sql, product.getArticleId(), product.getTitle(), product.getCategory());

		// Fetch article id
		sql = "SELECT articleId FROM Product WHERE title = ? and category=?";
		int articleId = jdbcTemplate.queryForObject(sql, Integer.class, product.getTitle(), product.getCategory());

		// Set article id
		product.setArticleId(articleId);
		return product;
	}

	public void updateProduct(Product article) {
		String sql = "UPDATE Product SET title=?, category=? WHERE articleId=?";
		jdbcTemplate.update(sql, article.getTitle(), article.getCategory(), article.getArticleId());
	}

	public void deleteProduct(int articleId) {
		String sql = "DELETE FROM Product WHERE articleId=?";
		jdbcTemplate.update(sql, articleId);
	}

	public boolean productExists(String title, String category) {
		String sql = "SELECT count(*) FROM Product WHERE title = ? and category=?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, title, category);
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void deleteArticle(int articleId) {
		// TODO Auto-generated method stub
		
	}
	 
}
