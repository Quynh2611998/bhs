package com.capstone.bhs.model.dto;

public class QueryBookingDTO extends ExtendsDTO {

	private Long id;
	private Long optionId;
	private Long hairStylistId;
	private String userName;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private String optionName;
	private String voucherOptionName;
	private String voucherUserName;
	private double price;
	private int duration;
	private double totalPrice;
	private double totalDuration;
	private String phoneNumber;
	private String note;
	private String status;
	private String dateTime;
	private String stylistName;
	private String imageSrc;
	private String startTime;
	private String endTime;
	private Long userProfileId;
	

	public QueryBookingDTO() {

	}

	public QueryBookingDTO(Long id, Long optionId, Long hairStylistId, String userName, String firstName,
			String lastName, String gender, String email, String optionName, String voucherOptionName,
			String voucherUserName, double price, int duration, double totalPrice, double totalDuration,
			String phoneNumber, String note, String status, String dateTime, String stylistName, String imageSrc,
			String startTime, String endTime, Long userProfileId) {
		this.id = id;
		this.optionId = optionId;
		this.hairStylistId = hairStylistId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.optionName = optionName;
		this.voucherOptionName = voucherOptionName;
		this.voucherUserName = voucherUserName;
		this.price = price;
		this.duration = duration;
		this.totalPrice = totalPrice;
		this.totalDuration = totalDuration;
		this.phoneNumber = phoneNumber;
		this.note = note;
		this.status = status;
		this.dateTime = dateTime;
		this.stylistName = stylistName;
		this.imageSrc = imageSrc;
		this.startTime = startTime;
		this.endTime = endTime;
		this.userProfileId = userProfileId;
	}

	public Long getOptionId() {
		return optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public Long getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(Long userProfileId) {
		this.userProfileId = userProfileId;
	}

	public Long getHairStylistId() {
		return hairStylistId;
	}

	public void setHairStylistId(Long hairStylistId) {
		this.hairStylistId = hairStylistId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOptionName() {
		return optionName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getVoucherOptionName() {
		return voucherOptionName;
	}

	public void setVoucherOptionName(String voucherOptionName) {
		this.voucherOptionName = voucherOptionName;
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

	public String getStylistName() {
		return stylistName;
	}

	public void setStylistName(String stylistName) {
		this.stylistName = stylistName;
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
