package rest.basics.provider;

import java.util.List;

public class EmpResponse {

	private String msg;
	private List<Employee> emps;

	public EmpResponse() {
		super();
	}

	public EmpResponse(String msg) {
		super();
		this.msg = msg;
	}

	public EmpResponse(String msg, List<Employee> emps) {
		super();
		this.msg = msg;
		this.emps = emps;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}