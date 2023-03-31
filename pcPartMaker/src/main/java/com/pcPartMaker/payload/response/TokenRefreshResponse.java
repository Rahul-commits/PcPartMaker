package com.pcPartMaker.payload.response;

import java.util.List;

public class TokenRefreshResponse {
	private String accessToken;
	private String refreshToken;
	private String tokenType = "Bearer";
	private List<String> roles;
	
	//public TokenRefreshResponse(String accessToken, String refreshToken, Set<Role> roles) {
	public TokenRefreshResponse(String accessToken, List<String> roles) {
		this.accessToken = accessToken;
		//this.refreshToken = refreshToken;
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
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}