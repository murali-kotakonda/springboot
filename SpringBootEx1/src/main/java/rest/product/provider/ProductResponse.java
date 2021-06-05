package rest.product.provider;

import java.util.HashSet;
import java.util.Set;

public class ProductResponse {

	Set<Product> products = new HashSet<Product>();

	public ProductResponse() {
		super();
	}

	public ProductResponse(Set<Product> products) {
		super();
		this.products = products;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
