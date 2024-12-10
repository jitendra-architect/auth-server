package com.example.authserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestClass {

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
}
