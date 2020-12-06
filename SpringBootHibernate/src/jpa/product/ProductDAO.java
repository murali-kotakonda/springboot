package jpa.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ProductDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	class ArticleRowMapper implements RowMapper<Product> {
		@Override
		public Product mapRow(ResultSet row, int rowNum) throws SQLException {
			Product article = new Product();
			article.setArticleId(row.getInt("articleId"));
			article.setTitle(row.getString("title"));
			article.setCategory(row.getString("category"));
			return article;
		}
	} 
	public Product getArticleById(int articleId) {
		String sql = "SELECT articleId, title, category FROM articles WHERE articleId = ?";
		RowMapper<Product> rowMapper = new BeanPropertyRowMapper<Product>(Product.class);
		try {
		Product article = jdbcTemplate.queryForObject(sql, rowMapper, articleId);
		return article;
		}catch(Exception ex) {
			throw new ServiceException("invalid Product ID.", "err03");
		}
	}

	public List<Product> getAllArticles() {
		String sql = "SELECT articleId, title, category FROM articles";
		// RowMapper<Article> rowMapper = new
		// BeanPropertyRowMapper<Article>(Article.class);
		RowMapper<Product> rowMapper = new ArticleRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	public void addArticle(Product article) {
		// Add article
		String sql = "INSERT INTO articles (articleId, title, category) values (?, ?, ?)";
		jdbcTemplate.update(sql, article.getArticleId(), article.getTitle(), article.getCategory());

		// Fetch article id
		sql = "SELECT articleId FROM articles WHERE title = ? and category=?";
		int articleId = jdbcTemplate.queryForObject(sql, Integer.class, article.getTitle(), article.getCategory());

		// Set article id
		article.setArticleId(articleId);
	}

	public void updateProduct(Product article) {
		String sql = "UPDATE articles SET title=?, category=? WHERE articleId=?";
		jdbcTemplate.update(sql, article.getTitle(), article.getCategory(), article.getArticleId());
	}

	public void deleteProduct(int articleId) {
		String sql = "DELETE FROM articles WHERE articleId=?";
		jdbcTemplate.update(sql, articleId);
	}

	public boolean productExists(String title, String category) {
		String sql = "SELECT count(*) FROM articles WHERE title = ? and category=?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, title, category);
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}
}
