package com.vbaggio.desafio03.dto.errors;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError {

	public List<FieldMessage> validationErrors = new ArrayList<>();
	
	public ValidationError(String message, String path, Instant moment) {
		super(message, path, moment);
	}

	public List<FieldMessage> getValidationErrors() {
		return validationErrors;
	}
	
	public void addValidationError(FieldMessage fieldMessage) {
		validationErrors.add(fieldMessage);
	}

}
