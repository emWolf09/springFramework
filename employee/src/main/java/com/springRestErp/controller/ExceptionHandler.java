package com.springRestErp.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springRestErp.CustomException.InsertingAddressFailedException;
import com.springRestErp.CustomException.InsertingEmployeeFaildedException;
import com.springRestErp.CustomException.NoAddressFoundException;
import com.springRestErp.CustomException.NoEmployeeFoundException;
import com.springRestErp.model.ExceptionResponse;

@RestControllerAdvice
public class ExceptionHandler {
	@org.springframework.web.bind.annotation.ExceptionHandler(value ={NoEmployeeFoundException.class, NoAddressFoundException.class})
	public ResponseEntity<?> handleFind(Exception e){
		ExceptionResponse res = new ExceptionResponse();
		res.setMessage(e.getMessage());
		res.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<ExceptionResponse>(res,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value ={InsertingAddressFailedException.class, InsertingEmployeeFaildedException.class})
	public ResponseEntity<?> handleInsert(Exception e){
		ExceptionResponse res = new ExceptionResponse();
		res.setMessage(e.getMessage());
		res.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
		return new ResponseEntity<ExceptionResponse>(res,HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value =Exception.class)
	public ResponseEntity<?> handleInvalidInput(Exception e){
		ExceptionResponse res = new ExceptionResponse();
		res.setMessage(e.getMessage());
		res.setStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<ExceptionResponse>(res,HttpStatus.BAD_REQUEST);
	}
}
