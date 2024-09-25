package com.vbaggio.desafio03.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vbaggio.desafio03.dto.errors.CustomError;
import com.vbaggio.desafio03.dto.errors.FieldMessage;
import com.vbaggio.desafio03.dto.errors.ValidationError;
import com.vbaggio.desafio03.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	private ValidationError validationError(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationError result = new ValidationError("Validation error", request.getRequestURI(), Instant.now());
		
		e.getBindingResult().getFieldErrors()
			.forEach(fe -> result.addValidationError(new FieldMessage(fe.getField(), fe.getDefaultMessage())));
		
		return result;
	}
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomError> ResourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomError(e.getMessage(), request.getRequestURI(), Instant.now()));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validationError(e, request));
	}
}
