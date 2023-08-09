package com.example.jwt.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.jwt.services.AuthUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	private AuthUserService authUserService;
	
	@Autowired
	private AuthEntryPoint authEntryPoint;
	
	@Bean
	public AuthFilter getAuthFilter() {
		return new AuthFilter();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	}
	
	 @Bean
	 public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	       
	      authProvider.setUserDetailsService(authUserService);
	      authProvider.setPasswordEncoder(passwordEncoder());
	   
	      return authProvider;
	  }
	 
	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		 http
		 .csrf(csrf -> csrf.disable())
		 .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPoint))
		 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		 .authorizeHttpRequests(
				  auth -> auth.requestMatchers(HttpMethod.POST, "/auth/api/signin").permitAll()
				              .requestMatchers(HttpMethod.POST, "/auth/api/signup").permitAll()
				              .anyRequest().authenticated());
		 
		 
		 http.authenticationProvider(authenticationProvider());
		 http.addFilterBefore(getAuthFilter(), UsernamePasswordAuthenticationFilter.class);
		 return http.build();
	 }
}
