package com.capstone.bhs.model.dto;

public class UserBookingDTO extends ExtendsDTO {

	private Long bookingId;
	private String username;
	private String dateTime;
	private String startTime;
	private String endTime;
	private String status;

	public UserBookingDTO() {
	}

	public UserBookingDTO(Long bookingId, String username, String dateTime, String startTime, String endTime,
			String status) {
		this.bookingId = bookingId;
		this.username = username;
		this.dateTime = dateTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
