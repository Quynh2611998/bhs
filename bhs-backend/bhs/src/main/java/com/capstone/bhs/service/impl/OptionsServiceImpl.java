package com.capstone.bhs.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.capstone.bhs.common.CommonFunction;
import com.capstone.bhs.model.dto.OptionSearchDTO;
import com.capstone.bhs.model.dto.OptionsDTO;
import com.capstone.bhs.model.entity.Images;
import com.capstone.bhs.model.entity.Options;
import com.capstone.bhs.model.vm.CreateOptionImageVM;
import com.capstone.bhs.model.vm.OptionsVM;
import com.capstone.bhs.repository.BookingRepository;
import com.capstone.bhs.repository.ImagesRepository;
import com.capstone.bhs.repository.OptionsRepository;
import com.capstone.bhs.service.OptionsService;

@Service
public class OptionsServiceImpl implements OptionsService {

	@SuppressWarnings("rawtypes")
	private CommonFunction commonFunction = new CommonFunction();

	@Autowired
	private OptionsRepository optionsRepository;

	@Autowired
	private ImagesRepository imageRepository;

	@Override
	public List<Options> getAllListOptions() {
		return optionsRepository.findAll();
	}

	@Override
	public Options createOption(OptionsVM optionsVM) {
		Optional<Options> name = optionsRepository.findOneByOptionName(optionsVM.getOptionName());
		if (name.isPresent())
			throw new RuntimeErrorException(null);

		Options option = new Options();
		option.setOptionName(optionsVM.getOptionName());
		option.setServiceId(optionsVM.getServiceId());
		option.setPrice(optionsVM.getPrice());
		option.setDuration(optionsVM.getDuration());
		option.setCreatedDate(Instant.now());
		option.setCreatedBy(optionsVM.getCreatedBy());
		option.setModifiedDate(Instant.now());
		option.setModifiedBy(optionsVM.getModifiedBy());
		optionsRepository.save(option);
		int limit = 1;
		for (CreateOptionImageVM image : optionsVM.getImages()) {
			if (limit <= 5) {
				Images obj = new Images();
				obj.setSrcImage(image.getImageSrc());
				obj.setOptionId(option.getId());
				obj.setAltImage(option.getOptionName());
				imageRepository.save(obj);
			} else
				break;
			limit++;
		}
		return option;
	}

	@Override
	public Optional<Options> updateOption(OptionsVM optionsVM, Long id) {
		return optionsRepository.findOneById(id).map(user -> {
			user.setOptionName(optionsVM.getOptionName());
			user.setServiceId(optionsVM.getServiceId());
			user.setPrice(optionsVM.getPrice());
			user.setDuration(optionsVM.getDuration());
			user.setCreatedBy(optionsVM.getCreatedBy());
			user.setModifiedBy(optionsVM.getModifiedBy());
			optionsRepository.save(user);
			int limit = 1;
			for (CreateOptionImageVM image : optionsVM.getImages()) {
				if (limit <= 5) {
					Images obj = new Images();
					obj.setSrcImage(image.getImageSrc());
					obj.setOptionId(user.getId());
					obj.setAltImage(user.getOptionName());
					imageRepository.save(obj);
				} else
					break;
				limit++;
			}
			return user;
		});
	}

	@Override
	public void delete(long[] ids) {
		for (long item : ids) {
			optionsRepository.deleteById(item);
		}
	}

	@Override
	public List<Map<String, Object>> searchOptionByKeyword(String keyword) {
		return optionsRepository.searchOptionByKeyword(keyword);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OptionSearchDTO> searchOptionDTOByKeyword(String keyword) {
		List<Map<String, Object>> queryData = optionsRepository.searchOptionDTOByKeyword(keyword);
		return commonFunction.convertListQueryResultToDTO(queryData);
	}

	@Override
	public Object getOptionById(Long id) {
		List<Map<String, Object>> queryData = optionsRepository.getOptionByOptionId(id);
		return queryData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OptionsDTO> getListAllJoinOptions() {
		List<Map<String, Object>> queryData = optionsRepository.lstDetailOptions();
		return commonFunction.convertListQueryResultToDTO(queryData);
	}

	@Override
	public List<Images> getImagesByOptionId(Long id) {
		return imageRepository.findByOptionId(id);
	}

	@Override
	public void deleteImages(long[] ids) {
		for (long item : ids) {
			imageRepository.deleteById(item);
		}

	}

	@Override
	public Object getListAllOptionImagePage(Pageable pageable) {
		Page<List<Map<String, Object>>> queryData = optionsRepository.lstAllOptionImagePage(pageable);
//		List<QueryScheduleDTO> result = new ArrayList<>();
//		ObjectMapper mapper = new ObjectMapper().registerModule(new ParameterNamesModule())
//				.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
//		for (Map<String, Object> res : queryData.get()) {
//			QueryScheduleDTO obj = mapper.convertValue(res, QueryScheduleDTO.class);
//			result.add(obj);
//		}
//		return commonFunction.convertListQueryResultToDTO(queryData);
		return queryData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OptionsDTO> getListViewAllOption() {
		List<Map<String, Object>> queryData = optionsRepository.lstViewAllOption();
		return commonFunction.convertListQueryResultToDTO(queryData);
	}

	@Override
	public Object getAllOptionByServiceId(Long id, Pageable pageable) {
		Page<List<Map<String, Object>>> queryData = optionsRepository.getAllOptionByServiceId(id, pageable);
		return queryData;
	}

	@Override
	public Page<List<Map<String, Object>>> ViewOptionAtHomePage(Pageable pageable) {
		return optionsRepository.ViewOptionAtHomePage(pageable);
	}

	@Override
	public List<Map<String, Object>> getDetailOneOptionById(Long id) {
		return optionsRepository.getDetailOneOptionById(id);
	}

	@Autowired
	BookingRepository bookingRepository;

	@Override
	public void deleteOption(Long id) {
		optionsRepository.deleteById(id);
	}

}
