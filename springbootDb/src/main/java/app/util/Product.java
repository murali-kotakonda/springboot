package app.util;

//@Document(collection = "emp")
public class Product {
	//@Id
	  int articleId;
	  String title;
	  String category;

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Product() {
		super();
	}

	public Product(int articleId, String title, String category) {
		super();
		this.articleId = articleId;
		this.title = title;
		this.category = category;
	}
}
