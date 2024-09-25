package com.vbaggio.desafio03.dto.errors;

import java.time.Instant;

public class CustomError {

	private String message;
	private String path;
	private Instant moment;

	public CustomError(String message, String path, Instant moment) {
		super();
		this.message = message;
		this.path = path;
		this.moment = moment;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}

	public Instant getMoment() {
		return moment;
	}

}
