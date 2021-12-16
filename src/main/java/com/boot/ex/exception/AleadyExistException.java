package com.boot.ex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AleadyExistException extends  Exception {

    public AleadyExistException(String message) {
        super(message);
    }
}