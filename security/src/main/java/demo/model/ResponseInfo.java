package demo.model;

import java.util.ArrayList;
import java.util.List;

public class ResponseInfo<T> {

	private String status;
	private List<T> data = new ArrayList<>();

	public ResponseInfo(String status, List<T> products) {
		super();
		this.status = status;
		this.data.addAll(products);
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
		this.data.add(product);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}