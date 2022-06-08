package com.capstone.bhs.model.dto;

public class getStylistDTO {
	private Long hairStylistId;
	private Long bookingId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String stylistName;
	private String username;
	private String dateTime;
	private String startTime;
	private String endTime;
	private int totalDuration;
	private String note;
	private String status;

	public getStylistDTO() {
	}

	public getStylistDTO(Long hairStylistId, Long bookingId, String firstName, String lastName, String phoneNumber,
			String stylistName, String username, String dateTime, String startTime, String endTime, int totalDuration,
			String note, String status) {
		this.hairStylistId = hairStylistId;
		this.bookingId = bookingId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.stylistName = stylistName;
		this.username = username;
		this.dateTime = dateTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.totalDuration = totalDuration;
		this.note = note;
		this.status = status;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getHairStylistId() {
		return hairStylistId;
	}

	public void setHairStylistId(Long hairStylistId) {
		this.hairStylistId = hairStylistId;
	}

	public String getStylistName() {
		return stylistName;
	}

	public void setStylistName(String stylistName) {
		this.stylistName = stylistName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(int totalDuration) {
		this.totalDuration = totalDuration;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}