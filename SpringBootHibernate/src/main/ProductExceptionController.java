package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ProductExceptionController {
   
	@ExceptionHandler(value = ServiceException.class)
   public ResponseEntity<Object> exception(ServiceException exception) {
      return new ResponseEntity<>(new ProductResponse(exception.getCode(), exception.getMsg()), HttpStatus.NOT_FOUND);
   }
   
   @ExceptionHandler({Exception.class})
   public ResponseEntity<Object> exception1(ServiceException exception) {
      return new ResponseEntity<>("Server Exception...", HttpStatus.SERVICE_UNAVAILABLE);
   }
}

class ProductResponse{
	String code;
	String msg;
	public ProductResponse(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
}
