package com.capstone.bhs.model.vm;

import java.util.List;

public class OptionsVM extends ExtendsVM {

	private String optionName;
	private Long serviceId;
	private Long duration;
	private double price;
	private List<CreateOptionImageVM> images;

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getOptionName() {
		return optionName;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public double getPrice() {
		return price;
	}

	public List<CreateOptionImageVM> getImages() {
		return images;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setImages(List<CreateOptionImageVM> images) {
		this.images = images;
	}

}
