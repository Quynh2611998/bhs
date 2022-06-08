package com.capstone.bhs.model.dto;

public class QueryScheduleDTO extends ExtendsDTO {
	private Long id;
	private String stylistName;
	private String dateTime;
	private String startTime;
	private String endTime;
	private String bookingNote;
	private String description;
	private String status;

	public QueryScheduleDTO() {
	}

	public Long getId() {
		return id;
	}

	public String getStylistName() {
		return stylistName;
	}

	public String getDateTime() {
		return dateTime;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setStylistName(String stylistName) {
		this.stylistName = stylistName;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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
