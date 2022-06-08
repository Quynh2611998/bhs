package com.capstone.bhs.model.dto;

public class QueryAllDTO extends ExtendsDTO {
	private Long userId;
	private Long bookingId;
	private String userName;
	private String dateTime;
	private String status;
	private double totalPrice;
	private double totalDuration;
	private String startTime;
	private String endTime;
	private Long optionId;
	private String optionName;
	private String voucherUserName;
	private double price;
	private int duration;
	private String stylistName;
	private String phoneNumber;

	public QueryAllDTO(Long userId, Long bookingId, String userName, String dateTime, String status, double totalPrice,
			double totalDuration, String startTime, String endTime, Long optionId, String optionName,
			String voucherUserName, double price, int duration, String stylistName, String phoneNumber) {

		this.userId = userId;
		this.bookingId = bookingId;
		this.userName = userName;
		this.dateTime = dateTime;
		this.status = status;
		this.totalPrice = totalPrice;
		this.totalDuration = totalDuration;
		this.startTime = startTime;
		this.endTime = endTime;
		this.optionId = optionId;
		this.optionName = optionName;
		this.voucherUserName = voucherUserName;
		this.price = price;
		this.duration = duration;
		this.stylistName = stylistName;
		this.phoneNumber = phoneNumber;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public String getStylistName() {
		return stylistName;
	}

	public void setStylistName(String stylistName) {
		this.stylistName = stylistName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public QueryAllDTO() {

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

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(double totalDuration) {
		this.totalDuration = totalDuration;
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

	public Long getOptionId() {
		return optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getVoucherUserName() {
		return voucherUserName;
	}

	public void setVoucherUserName(String voucherUserName) {
		this.voucherUserName = voucherUserName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
