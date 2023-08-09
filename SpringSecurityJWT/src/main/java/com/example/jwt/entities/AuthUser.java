package com.example.jwt.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "auth_user_tbl")
public class AuthUser {
	
	@Id
	@GeneratedValue
	@Column(name = "auth_user_id")
	private Integer id;
	
	@Column(name = "auth_name")
	private String name;
	
	@Column(name = "auth_user_password")
	private String password;
	
	@Column(name = "auth_email")
	private String email;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "auth_token_fk")
	private AuthToken token;

	public AuthUser(Integer id, String name, String password, String email, AuthToken token) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.token = token;
	}
	
	public AuthUser() {}

	@Override
	public String toString() {
		return "AuthUser [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", token="
				+ token + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AuthToken getToken() {
		return token;
	}

	public void setToken(AuthToken token) {
		this.token = token;
	}
}
