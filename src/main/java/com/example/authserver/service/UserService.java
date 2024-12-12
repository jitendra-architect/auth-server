package com.example.authserver.service;
/*
import com.example.authserver.entity.User;
import com.example.authserver.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
    private PasswordEncoder passwordEncoder;

	public void saveNewUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER"));
		userRepository.save(user);
	}

	public void saveAdmin(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER", "ADMIN"));
		userRepository.save(user);
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public Optional<User> findById(ObjectId id) {
		return userRepository.findById(id);
	}

	public void deleteById(ObjectId id) {
		userRepository.deleteById(id);
	}

	public void deleteByUsername(String username) {
		userRepository.deleteByUsername(username);
	}


	public User findByUserName(String userName) {
		return userRepository.findByUsername(userName);
	}
}
*/