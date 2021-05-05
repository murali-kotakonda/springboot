package mongo.model;


import java.util.ArrayList;
import java.util.List;

public class ProductResponse<T> {

	private String message;
	private List<T> products;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<T> getProducts() {
		if(products==null) {
			products = new ArrayList<T>();
		}
		return products;
	}
	public void setProducts(List<T> products) {
		this.products = products;
	}

	
}