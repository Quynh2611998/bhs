package com.capstone.bhs.model.vm;

import java.util.Date;
import java.util.List;

public class VoucherOptionsVM extends ExtendsVM {

	private Long id;
	private String name;
	private List<Long> optionId;
	private double discount;
	private Date dayStart;
	private Date dayExpire;
	private boolean isActived;

	public List<Long> getOptionId() {
		return optionId;
	}

	public void setOptionId(List<Long> optionId) {
		this.optionId = optionId;
	}

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

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Date getDayStart() {
		return dayStart;
	}

	public void setDayStart(Date dayStart) {
		this.dayStart = dayStart;
	}

	public Date getDayExpire() {
		return dayExpire;
	}

	public void setDayExpire(Date dayExpire) {
		this.dayExpire = dayExpire;
	}

	public boolean isActived() {
		return isActived;
	}

	public void setActived(boolean isActived) {
		this.isActived = isActived;
	}

}
