package com.capstone.bhs.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "booking_option_mapping")
public class BookingOptionMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "booking_id")
	private Long bookingId;

	@Column(name = "option_id")
	private Long optionId;

	public BookingOptionMapping() {
	}

	public BookingOptionMapping(Long id, Long bookingId, Long optionId) {
		super();
		this.id = id;
		this.bookingId = bookingId;
		this.optionId = optionId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public Long getOptionId() {
		return optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

}
