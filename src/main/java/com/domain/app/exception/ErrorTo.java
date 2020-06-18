package com.domain.app.exception;

import java.util.List;

public class ErrorTo {

	private List<ErrorResponse> errors;

	public ErrorTo(List<ErrorResponse> errors) {
		this.errors = errors;
	}

	public List<ErrorResponse> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorResponse> errors) {
		this.errors = errors;
	}

}
