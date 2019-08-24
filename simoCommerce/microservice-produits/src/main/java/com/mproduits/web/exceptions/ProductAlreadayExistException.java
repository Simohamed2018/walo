package com.mproduits.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductAlreadayExistException extends RuntimeException  {

	public ProductAlreadayExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductAlreadayExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	

}
