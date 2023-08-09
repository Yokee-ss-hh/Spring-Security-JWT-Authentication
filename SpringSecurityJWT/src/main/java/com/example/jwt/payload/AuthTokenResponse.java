package com.example.jwt.payload;

import java.time.LocalDateTime;

public class AuthTokenResponse {
	
	private String user;
	
	private String authenticationAccessToken;
	
	private String authenticationRefreshToken;
	
	private String tokenType;
	
	private LocalDateTime accessTokenCreated;
	
	private LocalDateTime accessTokenExpiration;
	
	private LocalDateTime refreshTokenCreated;
	
	private LocalDateTime refreshTokenExpiration;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAuthenticationAccessToken() {
		return authenticationAccessToken;
	}

	public void setAuthenticationAccessToken(String authenticationAccessToken) {
		this.authenticationAccessToken = authenticationAccessToken;
	}

	public String getAuthenticationRefreshToken() {
		return authenticationRefreshToken;
	}

	public void setAuthenticationRefreshToken(String authenticationRefreshToken) {
		this.authenticationRefreshToken = authenticationRefreshToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public LocalDateTime getAccessTokenCreated() {
		return accessTokenCreated;
	}

	public void setAccessTokenCreated(LocalDateTime accessTokenCreated) {
		this.accessTokenCreated = accessTokenCreated;
	}

	public LocalDateTime getAccessTokenExpiration() {
		return accessTokenExpiration;
	}

	public void setAccessTokenExpiration(LocalDateTime accessTokenExpiration) {
		this.accessTokenExpiration = accessTokenExpiration;
	}

	public LocalDateTime getRefreshTokenCreated() {
		return refreshTokenCreated;
	}

	public void setRefreshTokenCreated(LocalDateTime refreshTokenCreated) {
		this.refreshTokenCreated = refreshTokenCreated;
	}

	public LocalDateTime getRefreshTokenExpiration() {
		return refreshTokenExpiration;
	}

	public void setRefreshTokenExpiration(LocalDateTime refreshTokenExpiration) {
		this.refreshTokenExpiration = refreshTokenExpiration;
	}

	public AuthTokenResponse(String user, String authenticationAccessToken, String authenticationRefreshToken,
			String tokenType, LocalDateTime accessTokenCreated, LocalDateTime accessTokenExpiration,
			LocalDateTime refreshTokenCreated, LocalDateTime refreshTokenExpiration) {
		super();
		this.user = user;
		this.authenticationAccessToken = authenticationAccessToken;
		this.authenticationRefreshToken = authenticationRefreshToken;
		this.tokenType = tokenType;
		this.accessTokenCreated = accessTokenCreated;
		this.accessTokenExpiration = accessTokenExpiration;
		this.refreshTokenCreated = refreshTokenCreated;
		this.refreshTokenExpiration = refreshTokenExpiration;
	}
	
	public AuthTokenResponse() {}

	@Override
	public String toString() {
		return "AuthTokenResponse [user=" + user + ", authenticationAccessToken=" + authenticationAccessToken
				+ ", authenticationRefreshToken=" + authenticationRefreshToken + ", tokenType=" + tokenType
				+ ", accessTokenCreated=" + accessTokenCreated + ", accessTokenExpiration=" + accessTokenExpiration
				+ ", refreshTokenCreated=" + refreshTokenCreated + ", refreshTokenExpiration=" + refreshTokenExpiration
				+ "]";
	}
	
}
