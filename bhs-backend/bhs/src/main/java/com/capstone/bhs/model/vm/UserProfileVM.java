package com.capstone.bhs.model.vm;

import java.time.Instant;

public class UserProfileVM {
	private Long id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String gender;
	private Instant dateOfBirth;
	private String profileImage;
	public UserProfileVM() {
	}

	public UserProfileVM(Long id, String firstName, String lastName, String phoneNumber, String gender,
			Instant dateOfBirth, String profileImage) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.profileImage = profileImage;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public Instant getDateOfBirth() {
		return dateOfBirth;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setDateOfBirth(Instant dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	
}
