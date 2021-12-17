package com.boot.ex.controllers;

import com.boot.ex.exception.StatusCode;
import com.boot.ex.models.dto.UserResponseDTO;
import com.boot.ex.models.dto.UserRequestDTO;
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

		UserResponseDTO user = service.findByUserID(userid);

		return ResponseEntity.ok().body(new UserResponse(StatusCode.OK, "회원 정보 조회", user));
	}

	//회원 가입
	@PostMapping("/users")
	public ResponseEntity<?> insert(@Valid @RequestBody final UserRequestDTO request) throws Exception {

		UserResponseDTO user = service.register(request);

		return ResponseEntity.ok().body(new UserResponse(StatusCode.OK, "회원 가입", user));
	}

	//회원 정보 수정
	@PatchMapping("/users/{UserID}")
	public ResponseEntity<?> update(@PathVariable("UserID") String userid, final UserRequestDTO request) throws Exception {

		request.setUserid(userid);
		UserResponseDTO user = service.update(request);

		return ResponseEntity.ok().body(new UserResponse(StatusCode.OK, "회원 정보 수정", user));
	}

	//회원 삭제
	@DeleteMapping("/users/{UserID}")
	public ResponseEntity<?> delete(@PathVariable("UserID") String userid) throws Exception {

		UserResponseDTO user = service.delete(userid);

		return ResponseEntity.ok().body(new UserResponse(StatusCode.OK, "회원 삭제", user));
	}
}