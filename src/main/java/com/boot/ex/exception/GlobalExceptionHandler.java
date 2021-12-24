package com.boot.ex.exception;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.boot.ex.models.responses.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /*
        클라이언트에서 요청한 데이터의 리소스가 존재하지 않을 때 Handling
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException exception) {
        ErrorResponse response = new ErrorResponse(StatusCode.NOT_FOUND, exception.getMessage(), format.format(new Date()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /*
        클라이언트에서 요청한 데이터의 리소스가 이미 존재하는 경우 작업을 실행하지 않을 때 Handling
     */
    @ExceptionHandler(AleadyExistException.class)
    public ResponseEntity<?> alearyExistException(AleadyExistException exception) {
        ErrorResponse response = new ErrorResponse(StatusCode.NOT_FOUND, exception.getMessage(), format.format(new Date()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /*
        javax.validation.Valid or @Validated 으로 binding error 발생 시 Handling
        HttpMessageConverter에서 등록한 HttpMessageConverter binding 못할 경우 발생
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

        BindingResult bindingResult = exception.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();

        ErrorResponse response = new ErrorResponse(StatusCode.BAD_REQUEST, fieldError.getDefaultMessage(), format.format(new Date()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /*
        요청 경로는 있으나 지원하지 않는 HTTP Method일 경우 발생
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse response = new ErrorResponse(StatusCode.METHOD_NOT_ALLOWED, ex.getMessage(), format.format(new Date()));
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }
}