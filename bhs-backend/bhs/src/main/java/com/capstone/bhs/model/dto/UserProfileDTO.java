package com.capstone.bhs.model.dto;

import java.time.Instant;

public class UserProfileDTO extends ExtendsDTO {
	private Long id;
	private String userName;
	private String firstName;
	private String lastName;
	private String role;
	private String gender;
	private Instant DoB;
	private String email;
	private String phoneNumber;
	private boolean isLocked;
	private String imageProfile;

	public UserProfileDTO() {

	}

	public UserProfileDTO(Long id, String userName, String firstName, String lastName, String role, String gender,
			Instant doB, String email, String phoneNumber, boolean isLocked, String imageProfile) {
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.gender = gender;
		DoB = doB;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.isLocked = isLocked;
		this.imageProfile = imageProfile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Instant getDoB() {
		return DoB;
	}

	public void setDoB(Instant doB) {
		DoB = doB;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public String getImageProfile() {
		return imageProfile;
	}

	public void setImageProfile(String imageProfile) {
		this.imageProfile = imageProfile;
	}

}
