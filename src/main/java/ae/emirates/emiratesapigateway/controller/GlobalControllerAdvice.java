package ae.emirates.emiratesapigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.JsonMappingException;

import ae.emirates.emiratesapigateway.model.Response;

@ControllerAdvice
public class GlobalControllerAdvice {
	
	 @ExceptionHandler
     @ResponseStatus(HttpStatus.BAD_REQUEST)
     public ResponseEntity<Object> handleJsonMappingException(JsonMappingException ex) {
        
		 
         return new ResponseEntity<Object>(new Response<Object>(false,"Bad Request",null), HttpStatus.BAD_REQUEST);
     } 

}
