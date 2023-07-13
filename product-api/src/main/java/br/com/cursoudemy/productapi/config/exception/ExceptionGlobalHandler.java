package br.com.cursoudemy.productapi.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionGlobalHandler {
  
  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<?> handleValidationException(ValidationException validationException) {
    var datails = new ExceptionDetails();
    datails.setStatus(HttpStatus.BAD_REQUEST.value());
    datails.setMessage(validationException.getMessage());
    return new ResponseEntity<>(datails, HttpStatus.BAD_REQUEST);
    
  }
}
