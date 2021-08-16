package com.firtprojet.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFound(String message) {
		super(message);
	}

}
