package com.capstone.bhs.model.dto;

public class OptionsDTO extends ExtendsDTO {

	private Long id;
	private String optionName;
	private Long duration;
	private double price;

	public OptionsDTO(Long id, String optionName, Long duration, double price) {
		this.id = id;
		this.optionName = optionName;
		this.duration = duration;
		this.price = price;
	}

	public OptionsDTO() {
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
