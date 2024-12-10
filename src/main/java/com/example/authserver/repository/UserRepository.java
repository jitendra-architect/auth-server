package com.example.authserver.repository;

import com.example.authserver.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Object> {
	User findByUsername(String username);
	void deleteByUsername(String username);
}
