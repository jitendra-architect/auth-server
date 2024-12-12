package com.example.authserver.controller;

import com.example.authserver.entity.UserCredential;
import com.example.authserver.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserCredentialRepository userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<UserCredential> all = userService.findAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public void createUser(@RequestBody UserCredential user) {
        userService.save(user);
    }

    @GetMapping("clear-app-cache")
    public void clearAppCache(){
      //  appCache.init();
    }
}