package com.capstone.bhs.model.dto;

public class VoucherOptionMappingDTO extends ExtendsDTO {
	private Long id;
	private Long optionId;
	private Long voucherOptionId;
	private String optionName;
	private String voucherOptionName;

	public VoucherOptionMappingDTO() {
	}

	public VoucherOptionMappingDTO(Long id, Long optionId, Long voucherOptionId, String optionName,
			String voucherOptionName) {
		super();
		this.id = id;
		this.optionId = optionId;
		this.voucherOptionId = voucherOptionId;
		this.optionName = optionName;
		this.voucherOptionName = voucherOptionName;
	}

	public String getVoucherOptionName() {
		return voucherOptionName;
	}

	public void setVoucherOptionName(String voucherOptionName) {
		this.voucherOptionName = voucherOptionName;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

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
