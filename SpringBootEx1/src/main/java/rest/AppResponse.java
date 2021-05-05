package rest;

import java.util.List;

public class AppResponse {

	private String msg;

	public AppResponse() {
		super();
	}

	public AppResponse(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}