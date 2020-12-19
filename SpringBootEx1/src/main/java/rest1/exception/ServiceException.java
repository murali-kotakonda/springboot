package rest1.exception;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String msg;

	public ServiceException(String msg) {
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
