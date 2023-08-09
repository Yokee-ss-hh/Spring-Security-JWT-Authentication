package com.example.jwt.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.jwt.entities.AuthUser;
import com.example.jwt.repositories.AuthUserRepository;

@Service
public class AuthUserService implements UserDetailsService{
	
	@Autowired
	private AuthUserRepository authUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AuthUser user = authUserRepository.findByName(username);
		return AuthUserDetailsService.build(user);
	}
}
