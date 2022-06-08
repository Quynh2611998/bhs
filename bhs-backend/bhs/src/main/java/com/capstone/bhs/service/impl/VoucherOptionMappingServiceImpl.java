package com.capstone.bhs.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.bhs.common.CommonFunction;
import com.capstone.bhs.model.dto.VoucherOptionMappingDTO;
import com.capstone.bhs.model.entity.VoucherOptionMapping;
import com.capstone.bhs.model.entity.VoucherOptions;
import com.capstone.bhs.model.vm.VoucherOptionsVM;
import com.capstone.bhs.repository.VoucherOptionMappingRepository;
import com.capstone.bhs.repository.VoucherOptionRepository;
import com.capstone.bhs.service.VoucherOptionMappingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

@Service
public class VoucherOptionMappingServiceImpl implements VoucherOptionMappingService {

	@SuppressWarnings("rawtypes")
	private CommonFunction commonFunction = new CommonFunction();

	@Autowired
	private VoucherOptionRepository voucherOptionRepository;

	@Autowired
	private VoucherOptionMappingRepository voucherOptionMappingRepository;

	@Override
	public VoucherOptions createVoucherOption(VoucherOptionsVM voucherOptionsVM) {

		VoucherOptions voucherOptions = new VoucherOptions();

		voucherOptions.setName(voucherOptionsVM.getName());
		voucherOptions.setDiscount(voucherOptionsVM.getDiscount());
		voucherOptions.setDayStart(voucherOptionsVM.getDayStart());
		voucherOptions.setDayExpire(voucherOptionsVM.getDayExpire());
		voucherOptions.setActived(voucherOptionsVM.isActived());
		voucherOptions.setCreatedDate(Instant.now());
		voucherOptions.setCreatedBy(voucherOptionsVM.getCreatedBy());
		voucherOptions.setModifiedDate(Instant.now());
		voucherOptions.setModifiedBy(voucherOptionsVM.getModifiedBy());
		voucherOptionRepository.save(voucherOptions);

		for (Long optionId : voucherOptionsVM.getOptionId()) {
			VoucherOptionMapping voucherOptionMapping = new VoucherOptionMapping();
			voucherOptionMapping.setOptionId(optionId);
			voucherOptionMapping.setVoucherOptionId(voucherOptions.getId());
			voucherOptionMappingRepository.save(voucherOptionMapping);
		}
		return voucherOptions;
	}

	@Override
	public Optional<VoucherOptions> updateVoucherOption(VoucherOptionsVM voucherOptionsVM, Long id) {
		return voucherOptionRepository.findOneById(id).map(voucher -> {
			voucher.setName(voucherOptionsVM.getName());
			voucher.setDiscount(voucherOptionsVM.getDiscount());
			voucher.setDayStart(voucherOptionsVM.getDayStart());
			voucher.setDayExpire(voucherOptionsVM.getDayExpire());
			voucher.setActived(voucherOptionsVM.isActived());
			voucher.setCreatedDate(Instant.now());
			voucher.setModifiedDate(Instant.now());
			voucher.setModifiedBy(voucherOptionsVM.getModifiedBy());
			voucherOptionRepository.save(voucher);

			for (Long optionId : voucherOptionsVM.getOptionId()) {

				VoucherOptionMapping voucherOptionMapping = new VoucherOptionMapping();

				voucherOptionMapping.setOptionId(optionId);
				voucherOptionMapping.setVoucherOptionId(voucher.getId());
				voucherOptionMappingRepository.save(voucherOptionMapping);

			}
			return voucher;
		});
	}

	@Override
	public void deleteVoucherOption(long[] ids) {
		for (long item : ids) {
			voucherOptionRepository.deleteById(item);
		}
	}

	@Override
	public List<VoucherOptions> getAllListVoucherOption() {
		return voucherOptionRepository.findAll();
	}

	@Override
	public Optional<VoucherOptions> getVoucherOptionById(Long id) {
		return voucherOptionRepository.findOneById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VoucherOptionMappingDTO> getAllListVoucherOptionMapping() {
		List<Map<String, Object>> queryData = voucherOptionRepository.lstDetailVoucherOptionMapping();
		return commonFunction.convertListQueryResultToDTO(queryData);
	}

	@Override
	public Optional<VoucherOptions> activeVoucherOption(VoucherOptionsVM voucherOptionsVM, Long id) {
		return voucherOptionRepository.findOneById(id).map(voucher -> {
			boolean isActived = true;
			voucher.setActived(isActived);
			voucherOptionRepository.save(voucher);
			return voucher;
		});
	}

	@Override
	public void deleteVoucherOptionMapping(long[] ids) {
		for (long item : ids) {
			voucherOptionMappingRepository.deleteById(item);
		}

	}

	@Override
	public Object getAllOptionByVoucherOptionId(Long id) {
		List<Map<String, Object>> queryData = voucherOptionRepository.getAllOptionByVoucherOptionId(id);
		List<VoucherOptionMappingDTO> result = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper().registerModule(new ParameterNamesModule())
				.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
		for (Map<String, Object> res : queryData) {
			VoucherOptionMappingDTO obj = mapper.convertValue(res, VoucherOptionMappingDTO.class);
			result.add(obj);
		}
		return result;
	}

	@Override
	public Object getAllVoucherOptionByOptionId(Long id) {
		List<Map<String, Object>> queryData = voucherOptionRepository.getAllVoucherOptionByOptionId(id);
		List<VoucherOptionMappingDTO> result = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper().registerModule(new ParameterNamesModule())
				.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
		for (Map<String, Object> res : queryData) {
			VoucherOptionMappingDTO obj = mapper.convertValue(res, VoucherOptionMappingDTO.class);
			result.add(obj);
		}
		return result;
	}

}
