package com.capstone.bhs.model.vm;

public class RegisterVM {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String rePassword;
	private String gender;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public String getGender() {
		return gender;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
