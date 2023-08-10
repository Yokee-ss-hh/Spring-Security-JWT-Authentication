package com.example.jwt.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.configurations.AuthJwtUtils;
import com.example.jwt.entities.AuthToken;
import com.example.jwt.entities.AuthUser;
import com.example.jwt.payload.AuthTokenResponse;
import com.example.jwt.payload.SignInRequest;
import com.example.jwt.payload.SignUpRequest;
import com.example.jwt.repositories.AuthUserRepository;
import com.example.jwt.services.AuthUserDetailsService;
import com.example.jwt.services.AuthUserService;

import jakarta.servlet.http.HttpServletRequest;



@RestController
public class AuthControllers {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	AuthUserRepository repo;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	AuthJwtUtils utils;
	
	
	@PostMapping(path = "/auth/api/signin")
	public ResponseEntity<AuthTokenResponse> signInUser(@RequestBody SignInRequest signInRequest){
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
				new UsernamePasswordAuthenticationToken(signInRequest.getUserName(), signInRequest.getPassWord());
		
	    Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
	   
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    
	    String jwtAccessToken = utils.generateAccessToken(authentication);
	    String jwtRefreshToken = utils.generateRefreshToken(authentication);
	    
	    AuthUser user = repo.findByName(signInRequest.getUserName());
	    if(user != null) {
	    	AuthToken authToken = new AuthToken();
	    	authToken.setAccessToken(jwtAccessToken);
	    	authToken.setRefreshToken(jwtRefreshToken);
	    	user.setToken(authToken);
	    	repo.save(user);
	    AuthTokenResponse authTokenResponse = new AuthTokenResponse(
	    		user.getName()
	    		,jwtAccessToken
	    		,jwtRefreshToken
	    		,"JWT"
	    		,LocalDateTime.now()
	    		,LocalDateTime.ofInstant(utils.extractExpiration(jwtAccessToken).toInstant(), ZoneId.systemDefault())
	    		,LocalDateTime.now()
	    		,LocalDateTime.ofInstant(utils.extractExpiration(jwtRefreshToken).toInstant(), ZoneId.systemDefault())
	    		);
	    return ResponseEntity.status(HttpStatus.OK).body(authTokenResponse);
	    }
	    else {
	    	AuthTokenResponse a = new AuthTokenResponse();
		    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(a);
	    }
	}
	
	
	
	@PostMapping(path = "/auth/api/signup")
	public ResponseEntity<String> signUpUser(@RequestBody SignUpRequest signUpRequest){
		if(repo.existsByName(signUpRequest.getUserName())) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("User already exists, Try again with another username");
		}
		
		if(repo.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("Email already exists, Try to give another email");
		}
		
		AuthUser user = new AuthUser();
		user.setName(signUpRequest.getUserName());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(encoder.encode(signUpRequest.getPassWord()));
		AuthUser u = repo.save(user);
		System.out.println(u);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body("User sign up is completed and user created successfully");
	}
	
	@PostMapping(path = "/auth/api/refresh-access-token")
	public ResponseEntity<AuthTokenResponse> refreshToken(HttpServletRequest req) throws Exception{
		
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String generatedAccessToken = utils.generateAccessToken(authentication);
	    String generatedRefreshToken = utils.generateRefreshToken(authentication);

	    AuthUserDetailsService authUserDetailService = (AuthUserDetailsService) authentication.getPrincipal();
	    AuthUser user = repo.findByName(authUserDetailService.getUsername());
	    if(user != null) {
	    	user.getToken().setAccessToken(generatedAccessToken);
	    	user.getToken().setRefreshToken(generatedRefreshToken);
	    	repo.save(user);
	    AuthTokenResponse authTokenResponse = new AuthTokenResponse(
	    		user.getName()
	    		,generatedAccessToken
	    		,generatedRefreshToken
	    		,"JWT"
	    		,LocalDateTime.now()
	    		,LocalDateTime.ofInstant(utils.extractExpiration(generatedAccessToken).toInstant(), ZoneId.systemDefault())
	    		,LocalDateTime.now()
	    		,LocalDateTime.ofInstant(utils.extractExpiration(generatedRefreshToken).toInstant(), ZoneId.systemDefault())
	    		);
	    return ResponseEntity.status(HttpStatus.OK).body(authTokenResponse);
	    }
	    else {
	    	AuthTokenResponse a = new AuthTokenResponse();
		    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(a);
	    }
	}
}
