package app;

import java.util.ArrayList;
import java.util.List;

public class ResponseInfo<T> {

	private String status;
	private List<T> products = new ArrayList<>();

	public ResponseInfo(String status, List<T> products) {
		super();
		this.status = status;
		this.products.addAll(products);
	}

	public ResponseInfo() {
		super();
	}

	public ResponseInfo(String status) {
		super();
		this.status = status;
	}

	public ResponseInfo(String status, T product) {
		super();
		this.status = status;
		this.products.add(product);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<T> getProducts() {
		return products;
	}

	public void setProducts(List<T> products) {
		this.products = products;
	}

}