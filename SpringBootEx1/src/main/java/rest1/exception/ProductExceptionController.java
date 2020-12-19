package rest1.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionController {
   @ExceptionHandler(value = ServiceException.class)
   public ResponseEntity<Object> exception(ServiceException exception) {
      return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
   }
   
   @ExceptionHandler({IOException.class,IndexOutOfBoundsException.class})
   public ResponseEntity<Object> exception1(ServiceException exception) {
      return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
   }
}