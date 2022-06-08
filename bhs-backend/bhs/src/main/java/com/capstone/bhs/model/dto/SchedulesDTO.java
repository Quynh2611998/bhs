package com.capstone.bhs.model.dto;

import java.util.List;

public class SchedulesDTO {
	private String dateTime;
	private List<ShiftsDTO> shifts;

	public SchedulesDTO() {
	}

	public SchedulesDTO(String dateTime, List<ShiftsDTO> shifts) {
		this.dateTime = dateTime;
		this.shifts = shifts;
	}

	public String getDateTime() {
		return dateTime;
	}

	public List<ShiftsDTO> getShifts() {
		return shifts;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public void setShifts(List<ShiftsDTO> shifts) {
		this.shifts = shifts;
	}

}
