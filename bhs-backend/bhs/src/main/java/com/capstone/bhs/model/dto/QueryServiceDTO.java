package com.capstone.bhs.model.dto;

import java.util.List;

public class QueryServiceDTO {

	
	private String serviceName;
	private List<QueryOptionDTO> options;

	

	public QueryServiceDTO() {
	}



	public QueryServiceDTO(String serviceName, List<QueryOptionDTO> options) {
		this.serviceName = serviceName;
		this.options = options;
	}



	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public List<QueryOptionDTO> getOptions() {
		return options;
	}

	public void setOptions(List<QueryOptionDTO> options) {
		this.options = options;
	}

}
