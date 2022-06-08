package com.capstone.bhs.model.vm;

public class VoucherUserMappingVM {

	private Long id;
	private Long userId;
	private Long voucherUserId;

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
