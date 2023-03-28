package com.pcPartMaker.payload.response;

import java.util.HashSet;
import java.util.Set;

import com.pcPartMaker.model.Role;

public class TokenRefreshResponse {
	private String accessToken;
	private String refreshToken;
	private String tokenType = "Bearer";
	private Set<Role> roles = new HashSet<>();
	
	public TokenRefreshResponse(String accessToken, String refreshToken, Set<Role> roles) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.roles = roles;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String token) {
		this.accessToken = token;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}