package com.capstone.bhs.model.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ad_user")
public class AdUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "role")
	private String role;

	@Column(name = "created_date")
	private Instant createdDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_date")
	private Instant modifiedDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "active_key")
	private String activeKey;

	@Column(name = "reset_key")
	private String resetKey;

	private boolean isLocked;

	public AdUser() {
	}

	public AdUser(Long id, String username, String password, String role, Instant createdDate, String createdBy,
			Instant modifiedDate, String modifiedBy, boolean isActive, String activeKey, String resetKey,
			boolean isLocked) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		this.isActive = isActive;
		this.activeKey = activeKey;
		this.resetKey = resetKey;
		this.isLocked = isLocked;
	}

	public Long getId() {
		return id;
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

	public String getResetKey() {
		return resetKey;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setResetKey(String resetKey) {
		this.resetKey = resetKey;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

}
