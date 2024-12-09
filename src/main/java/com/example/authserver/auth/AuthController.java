package com.example.authserver.auth;

import com.example.authserver.entity.User;
import com.example.authserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

	private final UserService userService;

	public AuthController(UserService userService) {
		this.userService = userService;
	}

	//@Autowired
	//private  UserService userService;

	//private final AuthenticationManager authenticationManager;

//	@PostMapping("/register")
//	public ResponseEntity<String> register() {
//		//userService.createUser(user);
//		return ResponseEntity.ok("User registered successfully");
//	}

	@GetMapping({"/", "/home"})
	public ResponseEntity<String> home() {
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(username, password));
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		return ResponseEntity.ok("Home successful");
	}

	@PostMapping("/register")
	public ResponseEntity<String> createUser(@RequestBody User user) {

		userService.createUser(user);
		return ResponseEntity.ok("Register successful");
	}

	@GetMapping({"/login"})
	public String handleLogin() {
		return "custom_login";
	}

	@GetMapping("/user")
	public ResponseEntity<String> user() {
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(username, password));
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		return ResponseEntity.ok("User successful");
	}

	@GetMapping("/admin")
	public ResponseEntity<String> admin() {
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(username, password));
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		return ResponseEntity.ok("Admin login successful");
	}
}
