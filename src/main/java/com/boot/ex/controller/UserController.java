package com.boot.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.ex.domain.User.RequestDTO;
import com.boot.ex.domain.User.ResponseDTO;
import com.boot.ex.domain.User.ResponseDTO2;
import com.boot.ex.domain.User.ResponseDTO3;
import com.boot.ex.exception.ResourceNotFoundException;
import com.boot.ex.service.UserService;

@RestController
@RequestMapping("api/v1")
public class UserController {

	@Autowired private UserService service;
	
	//전체 회원 조회
	@GetMapping("/users")
	public ResponseDTO3 users() throws ResourceNotFoundException {
		
		return service.findAll();
	}
	
	//회원 ID 조회
	@GetMapping("/users/{UserID}")
	public ResponseDTO select(@PathVariable(value = "UserID") String userid) throws ResourceNotFoundException {
		
		return service.findByUserID(userid);
	}
	
	//회원 등록
	@PostMapping("/users")
	public ResponseDTO2 insert(RequestDTO dto) throws Exception {
		return service.saveUser(dto);
	}

	//회원 정보 수정
	@PatchMapping("/users")
	public ResponseDTO2 update(RequestDTO dto) throws ResourceNotFoundException {
		return service.updateUser(dto);
	}
	
	//회원 삭제
	@DeleteMapping("/users")
	public ResponseDTO2 delete(RequestDTO dto) throws ResourceNotFoundException {
		return service.deleteUser(dto);
	}
}