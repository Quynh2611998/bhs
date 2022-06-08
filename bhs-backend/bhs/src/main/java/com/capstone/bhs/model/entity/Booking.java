package com.capstone.bhs.model.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "user_profile_id")
	private Long userProfileId;

	@Column(name = "hair_stylist_id")
	private Long hairStylistId;

	@Column(name = "voucher_option_id")
	private Long voucherOptionId;

	@Column(name = "voucher_user_id")
	private Long voucherUserId;

	@Column(name = "total_duration")
	private int totalDuration;

	@Column(name = "price")
	private double price;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "note")
	private String note;

	@Column(name = "status")
	private String status;

	@Column(name = "created_date")
	private Instant createdDate;

	@Column(name = "date_time")
	private String dateTime;

	@Column(name = "start_time")
	private String startTime;

	@Column(name = "end_time")
	private String endTime;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_date")
	private Instant modifiedDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	public Booking() {

	}

	public Booking(Long id, Long userProfileId, Long hairStylistId, Long voucherOptionId, Long voucherUserId,
			int totalDuration, double price, String phoneNumber, String note, String status, Instant createdDate,
			String dateTime, String startTime, String endTime, String createdBy, Instant modifiedDate,
			String modifiedBy) {

		this.id = id;
		this.userProfileId = userProfileId;
		this.hairStylistId = hairStylistId;
		this.voucherOptionId = voucherOptionId;
		this.voucherUserId = voucherUserId;
		this.totalDuration = totalDuration;
		this.price = price;
		this.phoneNumber = phoneNumber;
		this.note = note;
		this.status = status;
		this.createdDate = createdDate;
		this.dateTime = dateTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public Long getHairStylistId() {
		return hairStylistId;
	}

	public void setHairStylistId(Long hairStylistId) {
		this.hairStylistId = hairStylistId;
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

	public void setVoucherUserId(Long voucherUserId) {
		this.voucherUserId = voucherUserId;
	}

	public int getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(int totalDuration) {
		this.totalDuration = totalDuration;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Instant getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Instant modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

}
