package com.capstone.bhs.model.dto;

import java.util.List;

public class QueryCategoryDTO {

	private String categoryName;
	private List<QueryServiceDTO> services;


	
	public QueryCategoryDTO(String categoryName, List<QueryServiceDTO> services) {
		super();
		this.categoryName = categoryName;
		this.services = services;
	}


	public QueryCategoryDTO() {
	}


	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<QueryServiceDTO> getServices() {
		return services;
	}

	public void setServices(List<QueryServiceDTO> services) {
		this.services = services;
	}

}
