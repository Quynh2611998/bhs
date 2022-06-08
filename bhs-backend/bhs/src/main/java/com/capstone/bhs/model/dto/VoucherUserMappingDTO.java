package com.capstone.bhs.model.dto;

public class VoucherUserMappingDTO extends ExtendsDTO {

	private Long id;
	private Long userId;
	private Long voucherUserId;

	public VoucherUserMappingDTO() {
	}

	public VoucherUserMappingDTO(Long id, Long userId, Long voucherUserId) {
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
