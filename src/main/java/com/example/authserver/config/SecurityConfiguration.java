package com.example.authserver.config;

import com.example.authserver.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/home", "/register/**").permitAll() // Allow unauthenticated access to specific endpoints
						.requestMatchers("/admin/**").hasRole("ADMIN") // Allow unauthenticated access to specific endpoints
						.requestMatchers("/user/**").hasRole("USER")
						.anyRequest().authenticated()) // Require authentication for all other requests
				//.formLogin(AbstractAuthenticationFilterConfigurer::permitAll);
				.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.loginPage("/login").permitAll());

		return http.build();
	}

	//In-Memory Users
	/*@Bean
	public UserDetailsService userDetailsService() {
		UserDetails normalUser = User.builder()
				.username("gc")
				.password("$2a$12$Fs8u1grmMjF8ASVmEWuT7ejLt3pCA1wS3KVtmp3oRFxn.nMpQVnNe")
				.roles("USER")
				.build();

		UserDetails ADMINUser = User.builder()
				.username("admin")
				.password("$2a$12$Fs8u1grmMjF8ASVmEWuT7ejLt3pCA1wS3KVtmp3oRFxn.nMpQVnNe")
				.roles("USER", "ADMIN")
				.build();

		return new InMemoryUserDetailsManager(normalUser, ADMINUser);
	}*/

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		//provider.setUserDetailsService(userDetailsService()); //In-Memory Data
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
}