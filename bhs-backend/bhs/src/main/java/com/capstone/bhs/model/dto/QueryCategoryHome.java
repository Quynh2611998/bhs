package com.capstone.bhs.model.dto;

public class QueryCategoryHome {

	private Long optionId;
	private String categoryName;
	private String serviceName;
	private String optionName;



	public QueryCategoryHome(Long optionId, String categoryName, String serviceName, String optionName) {
		
		this.optionId = optionId;
		this.categoryName = categoryName;
		this.serviceName = serviceName;
		this.optionName = optionName;
	}

	public QueryCategoryHome() {

	}

	public Long getOptionId() {
		return optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

}
