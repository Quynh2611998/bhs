package com.capstone.bhs.model.dto;

import java.util.List;

public class QueryUserBookingDTO {

	private Long userId;
	private String userName;

	private List<DateTimeBookingListAndDetailDTO> DateTime;

	
	
	public QueryUserBookingDTO(Long userId, String userName, List<DateTimeBookingListAndDetailDTO> dateTime) {
	
		this.userId = userId;
		this.userName = userName;
		DateTime = dateTime;
	}

	public QueryUserBookingDTO() {

	}

	public List<DateTimeBookingListAndDetailDTO> getDateTime() {
		return DateTime;
	}

	public void setDateTime(List<DateTimeBookingListAndDetailDTO> dateTime) {
		DateTime = dateTime;
	}



	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}



}
