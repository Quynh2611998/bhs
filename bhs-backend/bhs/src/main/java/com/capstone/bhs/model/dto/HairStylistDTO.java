package com.capstone.bhs.model.dto;

public class HairStylistDTO extends ExtendsDTO {
	private Long id;
	private String stylistName;
	private String gender;
	private String phoneNumber;

	public HairStylistDTO() {
	}

	public HairStylistDTO(Long id, String stylistName, String gender, String phoneNumber) {
		this.id = id;
		this.stylistName = stylistName;
		this.gender = gender;
		this.phoneNumber = phoneNumber;

	}

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

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
