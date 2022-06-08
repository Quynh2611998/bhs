package com.capstone.bhs.model.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "options")
public class Options {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "option_name")
	private String optionName;

	@Column(name = "service_id")
	private Long serviceId;

	@Column(name = "duration")
	private Long duration;

	@Column(name = "price")
	private double price;

	@Column(name = "created_date")
	private Instant createdDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_date")
	private Instant modifiedDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	public Options() {
	}

	public Options(Long id, String optionName, Long serviceId, Long duration, double price, Instant createdDate,
			String createdBy, Instant modifiedDate, String modifiedBy) {
		this.id = id;
		this.optionName = optionName;
		this.serviceId = serviceId;
		this.duration = duration;
		this.price = price;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
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

	public String getOptionName() {
		return optionName;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public double getPrice() {
		return price;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Instant getModifiedDate() {
		return modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setModifiedDate(Instant modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
