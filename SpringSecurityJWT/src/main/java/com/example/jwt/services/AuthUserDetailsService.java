package com.example.jwt.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.jwt.entities.AuthUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class AuthUserDetailsService implements UserDetails{
	
	static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String name;
	
	private String password;
	
	@JsonIgnore
	private String email;
	
	private Collection<? extends GrantedAuthority> authorities;

	public AuthUserDetailsService(Integer id, String name, String password, String email,
			Collection<? extends GrantedAuthority> authorities) {
		
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.authorities = authorities;
	}
	
	public static AuthUserDetailsService build(AuthUser user) {
		return new AuthUserDetailsService(user.getId(),
				user.getName(),
				user.getPassword(),
				user.getEmail(),
				new ArrayList<GrantedAuthority>()
				);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}	
}
