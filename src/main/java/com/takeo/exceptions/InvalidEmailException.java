package com.takeo.exceptions;

import lombok.Data;

@Data
public class InvalidEmailException extends RuntimeException {
	private String message;

	public InvalidEmailException(String message) {
		super();
		this.message = message;
	}
}
