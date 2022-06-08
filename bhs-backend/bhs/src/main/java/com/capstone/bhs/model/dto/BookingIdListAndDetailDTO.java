package com.capstone.bhs.model.dto;

import java.util.List;

public class BookingIdListAndDetailDTO {

	private Long bookingId;
	private String status;
	private double totalPrice;
	private List<OptionBookingListAndDetailDTO> Option;

	
	public BookingIdListAndDetailDTO(Long bookingId, String status, double totalPrice,
			List<OptionBookingListAndDetailDTO> option) {
		this.bookingId = bookingId;
		this.status = status;
		this.totalPrice = totalPrice;
		Option = option;
	}

	public BookingIdListAndDetailDTO() {
		super();
	}

	
	
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public List<OptionBookingListAndDetailDTO> getOption() {
		return Option;
	}

	public void setOption(List<OptionBookingListAndDetailDTO> option) {
		Option = option;
	}

}
