package com.boot.ex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CustomException {
	
	//요청 리소스 찾을 수 없을 때
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public static class ResourceNotFoundException extends Exception {
		
		private static final long serialVersionUID = 1L;

		public ResourceNotFoundException(String message) {
			super(message);
		}
	}
	
	//요청 리소스가 이미 있을 때
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public static class AleadyExistException extends Exception {

		private static final long serialVersionUID = 1L;

		public AleadyExistException(String message) {
			super(message);
		}
	}

	//요청 리소스가 잘못되었을 때
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public static class BadRequestException extends Exception {

		private static final long serialVersionUID = 1L;

		public BadRequestException(String message) {
			super(message);
		}
	}
}
