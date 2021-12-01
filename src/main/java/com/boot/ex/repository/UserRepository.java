package com.boot.ex.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.ex.domain.User;
import com.boot.ex.domain.User.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<User.UserEntity, Long> {
	
	Optional<UserEntity> findByUserid(String userid);
}