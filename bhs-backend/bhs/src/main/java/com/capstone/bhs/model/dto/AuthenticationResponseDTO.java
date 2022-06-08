package com.capstone.bhs.model.dto;

public class AuthenticationResponseDTO {
	private String role;
	private String token;

	public AuthenticationResponseDTO() {
	}

	public AuthenticationResponseDTO(String role, String token) {
		this.role = role;
		this.token = token;
	}

	public String getRole() {
		return role;
	}

	public String getToken() {
		return token;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
