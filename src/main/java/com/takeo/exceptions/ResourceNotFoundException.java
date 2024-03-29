package com.takeo.exceptions;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException{
	private String message;

	public ResourceNotFoundException(String message) {
		super();
		this.message = message;
	}
}
