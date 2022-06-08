package com.capstone.bhs.model.dto;

public class OptionSearchDTO {
	private Long id;
	private String optionName;
	private String serviceName;
	private String categoryName;
	
	public OptionSearchDTO() {
	}

	public OptionSearchDTO(Long id, String optionName, String serviceName, String categoryName) {
		this.id = id;
		this.optionName = optionName;
		this.serviceName = serviceName;
		this.categoryName = categoryName;
	}

	public Long getId() {
		return id;
	}

	public String getOptionName() {
		return optionName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
