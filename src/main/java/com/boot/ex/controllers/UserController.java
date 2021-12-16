package com.boot.ex.controllers;

import com.boot.ex.exception.StatusCode;
import com.boot.ex.models.data.UserData;
import com.boot.ex.models.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.ex.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class UserController {

	private final UserService service;

	@GetMapping("/users/{UserID}")
	public ResponseEntity<?> select(@PathVariable("UserID") String userid) throws Exception {

		UserData user = service.findByUserID(userid);

		return ResponseEntity.ok().body(new UserResponse(StatusCode.OK, "회원 정보 조회", user));
	}
}