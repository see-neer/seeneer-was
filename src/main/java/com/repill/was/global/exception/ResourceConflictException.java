package com.repill.was.global.exception;

import org.springframework.http.HttpStatus;

public class ResourceConflictException extends BusinessException {

	public ResourceConflictException() {
	}

	public ResourceConflictException(String message) {
		super(message);
	}

	public ResourceConflictException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceConflictException(Throwable cause) {
		super(cause);
	}

	public ResourceConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.CONFLICT;
	}

	@Override
	public boolean isNecessaryToLog() {
		return false;
	}
}
