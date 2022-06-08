package com.capstone.bhs.model.vm;

public class HairStylistVM extends ExtendsVM{
	private Long id;
	private String stylistName;
	private String gender;
	private String phoneNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStylistName() {
		return stylistName;
	}

	public void setStylistName(String stylistName) {
		this.stylistName = stylistName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhongNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
