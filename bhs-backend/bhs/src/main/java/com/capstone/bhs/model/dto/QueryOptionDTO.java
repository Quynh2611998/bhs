package com.capstone.bhs.model.dto;

public class QueryOptionDTO {
	private Long optionId;
	private String optionName;
	
	public QueryOptionDTO(Long optionId, String optionName) {
		
		this.optionId = optionId;
		this.optionName = optionName;
	}

	public QueryOptionDTO() {
	
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
	
	
}
