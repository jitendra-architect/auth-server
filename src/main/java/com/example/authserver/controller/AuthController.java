package com.example.authserver.controller;

import com.example.authserver.dto.AuthRequest;
import com.example.authserver.entity.UserCredential;
import com.example.authserver.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/users")
    public List<UserCredential> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping("/register")
    public String addNewUser(@RequestBody AuthRequest authRequest) {

        return service.saveUser(authRequest);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        try{
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
            // UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            // String jwt = jwtUtil.generateToken(userDetails.getUsername());
            if(authenticate.isAuthenticated())
            {
                return new ResponseEntity<>(service.generateToken(authRequest.getUsername()), HttpStatus.OK);
            }
            else {
                throw new RuntimeException("invalid access");
            }
        }catch (Exception e){
           // logger.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }
}