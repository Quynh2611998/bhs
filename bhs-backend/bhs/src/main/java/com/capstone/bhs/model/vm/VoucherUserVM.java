package com.capstone.bhs.model.vm;

import java.util.List;

public class VoucherUserVM extends ExtendsVM {

	private Long id;
	private String name;
	private List<Long> userId;
	private double discount;
	private String dayStart;
	private String dayExpire;
	private boolean isActived;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getUserId() {
		return userId;
	}

	public void setUserId(List<Long> userId) {
		this.userId = userId;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getDayStart() {
		return dayStart;
	}

	public void setDayStart(String dayStart) {
		this.dayStart = dayStart;
	}

	public String getDayExpire() {
		return dayExpire;
	}

	public void setDayExpire(String dayExpire) {
		this.dayExpire = dayExpire;
	}

	public boolean isActived() {
		return isActived;
	}

	public void setActived(boolean isActived) {
		this.isActived = isActived;
	}

}
