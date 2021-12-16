package com.boot.ex.exception;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.boot.ex.models.responses.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //리소스 요청 시 리소스가 없을 때
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException exception) {
        ErrorResponse error = new ErrorResponse(StatusCode.NOT_FOUND, exception.getMessage(), format.format(new Date()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //리소스가 이미 존재할 때
    @ExceptionHandler(AleadyExistException.class)
    public ResponseEntity<?> alearyExistException(AleadyExistException exception) {
        ErrorResponse error = new ErrorResponse(StatusCode.NOT_FOUND, exception.getMessage(), format.format(new Date()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //Valid시 값이 null이거나 공백이 있거나 존재하지 않을 때
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

        BindingResult bindingResult = exception.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();

        ErrorResponse error = new ErrorResponse(StatusCode.BAD_REQUEST, fieldError.getDefaultMessage(), format.format(new Date()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}