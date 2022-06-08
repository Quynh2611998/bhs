package com.capstone.bhs.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "voucher_user_mapping")
public class VoucherUserMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "voucher_user_id")
	private Long voucherUserId;

	public VoucherUserMapping() {
	}

	public VoucherUserMapping(Long id, Long userId, Long voucherUserId) {
		this.id = id;
		this.userId = userId;
		this.voucherUserId = voucherUserId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getVoucherUserId() {
		return voucherUserId;
	}

	public void setVoucherUserId(Long voucherUserId) {
		this.voucherUserId = voucherUserId;
	}
}
