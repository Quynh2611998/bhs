package com.capstone.bhs.service;

import java.util.List;
import java.util.Optional;

import com.capstone.bhs.model.dto.VoucherOptionMappingDTO;
import com.capstone.bhs.model.entity.VoucherOptions;
import com.capstone.bhs.model.vm.VoucherOptionsVM;

public interface VoucherOptionMappingService {

	public List<VoucherOptions> getAllListVoucherOption();

	public List<VoucherOptionMappingDTO> getAllListVoucherOptionMapping();

	public Optional<VoucherOptions> getVoucherOptionById(Long id);

	public VoucherOptions createVoucherOption(VoucherOptionsVM voucherOptionsVM);

	public Optional<VoucherOptions> updateVoucherOption(VoucherOptionsVM voucherOptionsVM, Long id);

	void deleteVoucherOption(long[] ids);

	public Optional<VoucherOptions> activeVoucherOption(VoucherOptionsVM voucherOptionsVM, Long id);
	
	void deleteVoucherOptionMapping(long[] ids);
	
	public Object getAllOptionByVoucherOptionId(Long id);
	
	public Object getAllVoucherOptionByOptionId(Long id);

}
