package com.capstone.bhs.model.dto;

public class OptionBookingListAndDetailDTO extends ExtendsDTO {

	private Long optionId;
	private String optionName;
	private String voucherUserName;
	private double price;
	private int duration;


	private double totalDuration;
	private String startTime;
	private String endTime;
	private String stylistName;
	private String phoneNumber;

	

	public OptionBookingListAndDetailDTO(Long optionId, String optionName, String voucherUserName, double price,
			int duration, double totalDuration, String startTime, String endTime, String stylistName,
			String phoneNumber) {

		this.optionId = optionId;
		this.optionName = optionName;
		this.voucherUserName = voucherUserName;
		this.price = price;
		this.duration = duration;
		this.totalDuration = totalDuration;
		this.startTime = startTime;
		this.endTime = endTime;
		this.stylistName = stylistName;
		this.phoneNumber = phoneNumber;
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

	public OptionBookingListAndDetailDTO() {
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

}
