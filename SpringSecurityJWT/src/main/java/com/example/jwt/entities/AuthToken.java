package com.example.jwt.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "auth_token_tbl")
public class AuthToken {
	
	@Id
	@GeneratedValue
	@Column(name = "auth_token_id")
	private Integer id;
	
	@Column(name = "auth_access_token")
	private String accessToken;
	
	@Column(name = "auth_refresh_token")
	private String refreshToken;

	public AuthToken(Integer id, String accessToken, String refreshToken) {
		super();
		this.id = id;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	
	public AuthToken() {}

	@Override
	public String toString() {
		return "AuthToken [id=" + id + ", accessToken=" + accessToken + ", refreshToken=" + refreshToken + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
