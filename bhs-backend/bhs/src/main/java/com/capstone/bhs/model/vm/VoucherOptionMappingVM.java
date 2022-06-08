package com.capstone.bhs.model.vm;

public class VoucherOptionMappingVM extends ExtendsVM {

	private Long id;
	private Long optionId;
	private Long voucherOptionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOptionId() {
		return optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public Long getVoucherOptionId() {
		return voucherOptionId;
	}

	public void setVoucherOptionId(Long voucherOptionId) {
		this.voucherOptionId = voucherOptionId;
	}

}
