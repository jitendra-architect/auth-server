package com.example.authserver.service;

import com.example.authserver.dto.AuthRequest;
import com.example.authserver.entity.UserCredential;
import com.example.authserver.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(AuthRequest authRequest) {
        UserCredential credential = new UserCredential();
        credential.setName(authRequest.getUsername());
        credential.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        repository.save(credential);
        return "user added to the system";
    }

    public List<UserCredential> getAllUsers() {
        return repository.findAll();
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
