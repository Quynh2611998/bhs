package com.capstone.bhs.model.dto;

public class ShiftsDTO extends ExtendsDTO {
	private String startTime;
	private String endTime;
	private String bookingNote;
	private String description;
	private String status;

	public ShiftsDTO() {
	}

	public ShiftsDTO(String startTime, String endTime, String bookingNote, String description, String status) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.bookingNote = bookingNote;
		this.description = description;
		this.status = status;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getBookingNote() {
		return bookingNote;
	}

	public String getDescription() {
		return description;
	}

	public String getStatus() {
		return status;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setBookingNote(String bookingNote) {
		this.bookingNote = bookingNote;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
