package com.capstone.bhs.model.dto;

import java.util.List;

public class StylistScheduleDTO {
	private Long id;
	private String stylistName;
	private List<SchedulesDTO> schedules;

	public StylistScheduleDTO() {
	}

	public StylistScheduleDTO(Long id, String stylistName, List<SchedulesDTO> schedules) {
		this.id = id;
		this.stylistName = stylistName;
		this.schedules = schedules;
	}

	public Long getId() {
		return id;
	}

	public String getStylistName() {
		return stylistName;
	}

	public List<SchedulesDTO> getSchedules() {
		return schedules;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStylistName(String stylistName) {
		this.stylistName = stylistName;
	}

	public void setSchedules(List<SchedulesDTO> schedules) {
		this.schedules = schedules;
	}

}
