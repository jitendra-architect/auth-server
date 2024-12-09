package com.example.authserver.entity;

import com.mongodb.lang.NonNull;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("users")
public class User {
	@Indexed(unique = true)
	@NonNull
	private String username;
	@NonNull
	private String password;
	private String email;
	private List<String> roles;
}