package com.capstone.bhs.model.vm;

public class ServicesVM extends ExtendsVM {
	
	private String serviceName;
	private Long categoryId;

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
