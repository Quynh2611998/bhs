package com.capstone.bhs.model.entity;

import java.time.Instant;
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
@Table(name = "hair_stylist")
public class HairStylist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "stylist_name")
	private String stylistName;

	@Column(name = "gender")
	private String gender;

	@Column(name = "phone")
	private String phoneNumber;

	@Column(name = "created_date")
	private Instant createdDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_date")
	private Instant modifiedDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "stylist")
	@JsonIgnoreProperties(value = { "scheduleBooking", "stylist", "shift" }, allowSetters = true)
	private Set<StylistSchedule> stylistSchedule = new HashSet<>();

	public HairStylist() {
	}

	public HairStylist(Long id, String stylistName, String gender, String phoneNumber, Instant createdDate,
			String createdBy, Instant modifiedDate, String modifiedBy, Set<StylistSchedule> stylistSchedule) {
		this.id = id;
		this.stylistName = stylistName;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		this.stylistSchedule = stylistSchedule;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HairStylist id(Long id) {
		this.id = id;
		return this;
	}

	public String getStylistName() {
		return this.stylistName;
	}

	public HairStylist stylistName(String stylistName) {
		this.stylistName = stylistName;
		return this;
	}

	public void setStylistName(String stylistName) {
		this.stylistName = stylistName;
	}

	public String getGender() {
		return this.gender;
	}

	public HairStylist gender(String gender) {
		this.gender = gender;
		return this;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public HairStylist phoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Instant getCreatedDate() {
		return this.createdDate;
	}

	public HairStylist createdDate(Instant createdDate) {
		this.createdDate = createdDate;
		return this;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public HairStylist createdBy(String createdBy) {
		this.createdBy = createdBy;
		return this;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Instant getModifiedDate() {
		return this.modifiedDate;
	}

	public HairStylist modifiedDate(Instant modifiedDate) {
		this.modifiedDate = modifiedDate;
		return this;
	}

	public void setModifiedDate(Instant modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public HairStylist modifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
		return this;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Set<StylistSchedule> getStylistSchedule() {
		return this.stylistSchedule;
	}

	public HairStylist stylistSchedule(Set<StylistSchedule> stylistSchedule) {
		this.setStylistSchedules(stylistSchedule);
		return this;
	}

	public HairStylist addStylistSchedule(StylistSchedule stylistSchedule) {
		this.stylistSchedule.add(stylistSchedule);
		stylistSchedule.setStylist(this);
		return this;
	}

	public HairStylist removeStylistSchedule(StylistSchedule stylistSchedule) {
		this.stylistSchedule.remove(stylistSchedule);
		stylistSchedule.setStylist(null);
		return this;
	}

	public void setStylistSchedules(Set<StylistSchedule> stylistSchedule) {
		if (this.stylistSchedule != null) {
			this.stylistSchedule.forEach(i -> i.setStylist(null));
		}
		if (stylistSchedule != null) {
			stylistSchedule.forEach(i -> i.setStylist(this));
		}
		this.stylistSchedule = stylistSchedule;
	}
}
