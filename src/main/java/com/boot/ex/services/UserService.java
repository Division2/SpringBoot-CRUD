package com.boot.ex.services;

import com.boot.ex.exception.AleadyExistException;
import com.boot.ex.exception.ResourceNotFoundException;
import com.boot.ex.models.dto.UserResponseDTO;
import com.boot.ex.models.entities.User;
import com.boot.ex.models.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boot.ex.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
	private final PasswordEncoder passwordEncoder;
	private final UserRepository repository;

	//전체 회원 조회
	@Transactional(readOnly = true)
	public List<UserResponseDTO> findAll() throws Exception {

		List<UserResponseDTO> dto = new ArrayList<>();
		for(int i=0; i<repository.findAll().size(); i++){
			dto.add(repository.findAll().get(i).getData());
		}
		return dto;
	}

	//회원 ID 조회
	@Transactional(readOnly = true)
	public UserResponseDTO findByUserID(String userid) throws Exception {
		return repository.findByUserid(userid).orElseThrow(() -> new ResourceNotFoundException("요청한 리소스를 찾을 수 없습니다.")).getData();
	}

	//회원 가입
	@Transactional
	public UserResponseDTO register(UserRequestDTO request) throws Exception {

		Optional<User> user = repository.findByUserid(request.getUserid());

		if (user.isPresent()) {
			throw new AleadyExistException("이미 등록된 사용자입니다.");
		}

		return save(request).getData();
	}

	@Transactional
	public UserResponseDTO update(UserRequestDTO request) throws Exception {

		Optional<User> user = repository.findByUserid(request.getUserid());

		if (user.isPresent()) {
			User result = repository.save(User.builder()
					.aid(user.get().getAid())
					.userid(request.getUserid())
					.password(passwordEncoder.encode(request.getPassword()))
					.name(request.getName())
					.phone(request.getPhone())
					.build());
			return result.getData();
		}
		else {
			throw new ResourceNotFoundException("요청한 리소스를 찾을 수 없습니다.");
		}
	}

	@Transactional
	public UserResponseDTO delete(String userid) throws Exception {
		Optional<User> user = repository.findByUserid(userid);

		if (user.isPresent()) {
			User user2 = user.get();
			user2.setDeleted(true);
		}
		return user.get().getData();
	}

	private User save(UserRequestDTO request) {
		User result = repository.save(User.builder()
				.userid(request.getUserid())
				.password(passwordEncoder.encode(request.getPassword()))
				.name(request.getName())
				.phone(request.getPhone())
				.build());
		return result;
	}
}