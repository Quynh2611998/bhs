package com.capstone.bhs.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "shift")
public class Shift {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "start_time")
	private String startTime;

	@Column(name = "end_time")
	private String endTime;

	@Column(name = "duration")
	private Long duration;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "shift")
	@JsonIgnoreProperties(value = { "scheduleBookings", "stylist", "shift" }, allowSetters = true)
	private Set<StylistSchedule> stylistSchedules = new HashSet<>();

	public Shift() {

	}

	public Shift(Long id, String startTime, String endTime, Long duration, Set<StylistSchedule> stylistSchedules) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = duration;
		this.stylistSchedules = stylistSchedules;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Set<StylistSchedule> getStylistSchedules() {
		return this.stylistSchedules;
	}

	public Shift stylistSchedules(Set<StylistSchedule> stylistSchedules) {
		this.setStylistSchedules(stylistSchedules);
		return this;
	}

	public Shift addStylistSchedule(StylistSchedule stylistSchedule) {
		this.stylistSchedules.add(stylistSchedule);
		stylistSchedule.setShiftId(this);
		return this;
	}

	public Shift removeStylistSchedule(StylistSchedule stylistSchedule) {
		this.stylistSchedules.remove(stylistSchedule);
		stylistSchedule.setShiftId(null);
		return this;
	}

	public void setStylistSchedules(Set<StylistSchedule> stylistSchedules) {
		if (this.stylistSchedules != null) {
			this.stylistSchedules.forEach(i -> i.setShiftId(null));
		}
		if (stylistSchedules != null) {
			stylistSchedules.forEach(i -> i.setShiftId(this));
		}
		this.stylistSchedules = stylistSchedules;
	}

}
