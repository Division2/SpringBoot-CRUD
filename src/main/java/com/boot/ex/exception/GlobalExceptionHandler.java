package com.boot.ex.exception;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.boot.ex.response.StatusCode;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(StatusCode.NOT_FOUND, exception.getMessage(), format.format(new Date()), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AleadyExistException.class)
	public ResponseEntity<?> aleadyExistException(AleadyExistException exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(StatusCode.CONFLICT, exception.getMessage(), format.format(new Date()), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> badRequestException(BadRequestException exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(StatusCode.BAD_REQUEST, exception.getMessage(), format.format(new Date()), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(StatusCode.INTERNAL_SERVER_ERROR, exception.getMessage(), format.format(new Date()), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(StatusCode.BAD_REQUEST, "Validation Failed", format.format(new Date()), exception.getBindingResult().toString());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}