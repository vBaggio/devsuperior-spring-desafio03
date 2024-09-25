package com.vbaggio.desafio03.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
		super("ID not found");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
