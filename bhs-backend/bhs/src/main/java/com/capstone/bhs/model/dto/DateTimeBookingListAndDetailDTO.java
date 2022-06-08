package com.capstone.bhs.model.dto;

import java.util.List;

public class DateTimeBookingListAndDetailDTO {
	private String dateTime;

	private List<BookingIdListAndDetailDTO> Booking;

	public DateTimeBookingListAndDetailDTO(String dateTime, List<BookingIdListAndDetailDTO> booking) {

		this.dateTime = dateTime;
		Booking = booking;
	}

	public DateTimeBookingListAndDetailDTO() {

	}

	public List<BookingIdListAndDetailDTO> getBooking() {
		return Booking;
	}

	public void setBooking(List<BookingIdListAndDetailDTO> booking) {
		Booking = booking;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

}
