package com.boot.ex.controllers;

import com.boot.ex.exception.StatusCode;
import com.boot.ex.models.data.UserData;
import com.boot.ex.models.requests.UserRequest;
import com.boot.ex.models.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.boot.ex.services.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class UserController {

	private final UserService service;

	//회원 ID 조회
	@GetMapping("/users/{UserID}")
	public ResponseEntity<?> select(@PathVariable("UserID") String userid) throws Exception {

		UserData user = service.findByUserID(userid);

		return ResponseEntity.ok().body(new UserResponse(StatusCode.OK, "회원 정보 조회", user));
	}

	//회원 가입
	@PostMapping("/users")
	public ResponseEntity<?> Insert(@Valid @RequestBody UserRequest request) throws Exception {

		UserData user = service.Insert(request).getData();

		return ResponseEntity.ok().body(new UserResponse(StatusCode.OK, "회원 가입", user));
	}
}