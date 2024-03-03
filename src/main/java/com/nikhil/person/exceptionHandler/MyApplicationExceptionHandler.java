package com.nikhil.person.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nikhil.person.responsestructure.ResponseStructure;

@RestControllerAdvice
public class MyApplicationExceptionHandler {

	@ExceptionHandler(PersonNotFound.class)
	public ResponseStructure<String> PersonNotFound(PersonNotFound personNotFound) {

		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		response.setData("Not Found");

		return response;

	}
}
