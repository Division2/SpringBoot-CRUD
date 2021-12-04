package com.boot.ex.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.ex.domain.User.RequestDTO;
import com.boot.ex.domain.User.ResponseDTO;
import com.boot.ex.domain.User.ResponseDTO2;
import com.boot.ex.domain.User.ResponseDTO3;
import com.boot.ex.domain.User.UserEntity;
import com.boot.ex.exception.CustomException;
import com.boot.ex.exception.StatusCode;
import com.boot.ex.exception.CustomException.ResourceNotFoundException;
import com.boot.ex.repository.UserRepository;

@Service
public class UserService {
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired UserRepository repository;
	
	//전체 회원 조회
	public ResponseDTO3 findAll() throws ResourceNotFoundException {
		List<UserEntity> users = repository.findAll();
		
		if (users.isEmpty()) {
			throw new ResourceNotFoundException("요청 리소스를 찾을 수 없습니다.");
		}
		
		return new ResponseDTO3(StatusCode.OK, "회원 전체 조회", format.format(new Date()), users);
	}
	
	//회원 ID 조회
	public ResponseDTO findByUserID(String userid) throws ResourceNotFoundException {
		UserEntity user = repository.findByUserid(userid).orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 사용자입니다."));
		
		return new ResponseDTO(StatusCode.OK, "회원 정보 조회", format.format(new Date()), user);
	}
	
	//회원 등록
	public ResponseDTO2 saveUser(RequestDTO dto) throws Exception {
		Optional<UserEntity> user =  repository.findByUserid(dto.getUserid());
		
		if (dto.getUserid() == null || dto.getPassword() == null) {
			throw new CustomException.BadRequestException("잘못된 요청 파라미터입니다.");
		}
		if (user.isPresent()) {
			throw new CustomException.AleadyExistException("이미 등록된 사용자입니다.");
		}
		
		repository.save(dto.toEntity());
		return new ResponseDTO2(StatusCode.CREATED, "회원 가입", format.format(new Date()));
	}
	
	//회원 정보 수정
	public ResponseDTO2 updateUser(RequestDTO dto) throws ResourceNotFoundException {
		Optional<UserEntity> user = repository.findByUserid(dto.getUserid());
		if(user.isPresent()) {
			dto.setAid(user.get().getAid());
			repository.save(dto.toEntity());
		}
		else {
			throw new ResourceNotFoundException("존재하지 않는 사용자입니다.");
		}
		
		return new ResponseDTO2(StatusCode.OK, "회원 정보 수정", format.format(new Date()));
	}
	
	//회원 삭제
	public ResponseDTO2 deleteUser(RequestDTO dto) throws ResourceNotFoundException {
		Optional<UserEntity> user = repository.findByUserid(dto.getUserid());
		if(user.isPresent()) {
			dto.setAid(user.get().getAid());
			repository.delete(dto.toEntity());
		}
		else {
			throw new ResourceNotFoundException("존재하지 않는 사용자입니다.");
		}
		return new ResponseDTO2(StatusCode.OK, "회원 삭제", format.format(new Date()));
	}
}