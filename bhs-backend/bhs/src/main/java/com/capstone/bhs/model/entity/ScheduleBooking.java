package com.capstone.bhs.model.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "schedule_booking")
public class ScheduleBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "booking_note")
	private String bookingNote;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	private String status;

	@Column(name = "created_date")
	private Instant createdDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_date")
	private Instant modifiedDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@ManyToOne
	@JsonIgnoreProperties(value = { "scheduleBookings", "stylist", "shift" }, allowSetters = true)
	private StylistSchedule schedule;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ScheduleBooking id(Long id) {
		this.id = id;
		return this;
	}

	public String getBookingNote() {
		return this.bookingNote;
	}

	public ScheduleBooking bookingNote(String bookingNote) {
		this.bookingNote = bookingNote;
		return this;
	}

	public void setBookingNote(String bookingNote) {
		this.bookingNote = bookingNote;
	}

	public String getDescription() {
		return this.description;
	}

	public ScheduleBooking description(String description) {
		this.description = description;
		return this;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return this.status;
	}

	public ScheduleBooking status(String status) {
		this.status = status;
		return this;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public StylistSchedule getSchedule() {
		return this.schedule;
	}

	public ScheduleBooking scheduleId(StylistSchedule stylistSchedule) {
		this.setSchedule(stylistSchedule);
		return this;
	}

	public void setSchedule(StylistSchedule stylistSchedule) {
		this.schedule = stylistSchedule;
	}

}
