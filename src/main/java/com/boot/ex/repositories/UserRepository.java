package com.boot.ex.repositories;

import java.util.Optional;

import com.boot.ex.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUserid(String userid);
}