package com.capstone.bhs.model.dto;

public class ServicesDTO extends ExtendsDTO {
	private Long id;
	private String serviceName;
	private Long categoryId;
	private String categoryName;

	public ServicesDTO() {
	}

	public ServicesDTO(Long id, String serviceName, Long categoryId, String categoryName) {
		this.id = id;
		this.serviceName = serviceName;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}
