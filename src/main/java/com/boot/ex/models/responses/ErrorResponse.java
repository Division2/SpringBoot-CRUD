package com.boot.ex.models.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ErrorResponse {

    private int status;
    private String message;
    private String date;

    public ErrorResponse(int status, String message, String date) {
        this.status = status;
        this.message = message;
        this.date = date;
    }
}