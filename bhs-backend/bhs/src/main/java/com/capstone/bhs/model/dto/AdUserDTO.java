package com.capstone.bhs.model.dto;

import java.time.Instant;

public class AdUserDTO {
	private Long Id;
	private String username;
	private String password;
	private String role;
	private Instant createdDate;
	private String createdBy;
	private Instant modifiedDate;
	private String modifiedBy;
	private boolean isActive;
	private String activeKey;
	private boolean isLocked;

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Long getId() {
		return Id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
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

	public boolean isActive() {
		return isActive;
	}

	public String getActiveKey() {
		return activeKey;
	}

	public void setId(Long id) {
		Id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
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

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setActiveKey(String activeKey) {
		this.activeKey = activeKey;
	}

}
