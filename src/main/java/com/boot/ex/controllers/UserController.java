package com.boot.ex.controllers;

import com.boot.ex.exception.StatusCode;
import com.boot.ex.models.data.UserData;
import com.boot.ex.models.responses.ErrorResponse;
import com.boot.ex.models.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.ex.services.UserService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class UserController {

	private final UserService service;

	@GetMapping("/users/{UserID}")
	public ResponseEntity<?> select(@PathVariable("UserID") String userid) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		UserData user = service.findByUserID(userid);

		if (user == null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse(StatusCode.INTERNAL_SERVER_ERROR, "조회 실패", format.format(new Date())));
		}
		return ResponseEntity.ok().body(new UserResponse(StatusCode.OK, "회원 정보 조회", user));
	}
}