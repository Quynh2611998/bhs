package com.capstone.bhs.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "voucher_option_mapping")
public class VoucherOptionMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "option_id")
	private Long optionId;

	@Column(name = "voucher_option_id")
	private Long voucherOptionId;

	public VoucherOptionMapping() {
	}

	public VoucherOptionMapping(Long id, Long optionId, Long voucherOptionId) {
		super();
		this.id = id;
		this.optionId = optionId;
		this.voucherOptionId = voucherOptionId;
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
