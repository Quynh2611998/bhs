package com.capstone.bhs.model.vm;

import java.util.List;

public class CreateBookingVM extends ExtendsVM {
	private Long id;
	private Long userProfileId;
	private Long optionId;
	private Long voucherOptionId;
	private String phoneNumer;
	private Long voucherUserId;
	private Long hairStylistId;
	private Long adUserId;
	private double totalPrice;
	private int totalDuration;
	private String note;
	private String status;
	private String dateTime;
	private String imageSrc;
	private String startTime;
	private String endTime;
	private List<Long> optionBookingMappingId;

	public String getPhoneNumer() {
		return phoneNumer;
	}

	public void setPhoneNumer(String phoneNumer) {
		this.phoneNumer = phoneNumer;
	}

	public List<Long> getOptionBookingMappingId() {
		return optionBookingMappingId;
	}

	public void setOptionBookingMappingId(List<Long> optionBookingMappingId) {
		this.optionBookingMappingId = optionBookingMappingId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(Long userProfileId) {
		this.userProfileId = userProfileId;
	}

	public Long getOptionId() {
		return optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public Long getVoucherOptionId() {
		return voucherOptionId;
	}

	public void setVoucherOptionId(Long voucherOptionId) {
		this.voucherOptionId = voucherOptionId;
	}

	public Long getVoucherUserId() {
		return voucherUserId;
	}

	public Long getAdUserId() {
		return adUserId;
	}

	public void setAdUserId(Long adUserId) {
		this.adUserId = adUserId;
	}

	public void setVoucherUserId(Long voucherUserId) {
		this.voucherUserId = voucherUserId;
	}

	public Long getHairStylistId() {
		return hairStylistId;
	}

	public void setHairStylistId(Long hairStylistId) {
		this.hairStylistId = hairStylistId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(int totalDuration) {
		this.totalDuration = totalDuration;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
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
