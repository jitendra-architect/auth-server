package com.example.authserver.service;

import com.example.authserver.entity.User;
import com.example.authserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
    private PasswordEncoder passwordEncoder;

	public void createUser(User user) {
		if (userRepository.findByUsername(user.getUsername()).isPresent()) {
			throw new IllegalArgumentException("User already exists");
		}
		if(user.getRoles() == null || user.getRoles().isEmpty()) {
			user.setRoles(List.of("USER"));
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Optional<User> findById(Object id) {
		return userRepository.findById(id);
	}
	public void deleteById(Object id) {
		userRepository.deleteById(id);
	}
}
