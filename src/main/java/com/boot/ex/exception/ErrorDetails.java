package com.boot.ex.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
	private int status;
	private String message;
	private String date;
	private String details;
}