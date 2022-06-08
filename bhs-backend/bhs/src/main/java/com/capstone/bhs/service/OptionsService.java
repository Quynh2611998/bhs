package com.capstone.bhs.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.capstone.bhs.model.dto.OptionSearchDTO;
import com.capstone.bhs.model.dto.OptionsDTO;
import com.capstone.bhs.model.entity.Images;
import com.capstone.bhs.model.entity.Options;
import com.capstone.bhs.model.vm.OptionsVM;

public interface OptionsService {

	List<Options> getAllListOptions();

	public Options createOption(OptionsVM optionsVM);

	public Optional<Options> updateOption(OptionsVM optionsVM, Long id);

	void delete(long[] ids);

	void deleteImages(long[] ids);

	List<Map<String, Object>> searchOptionByKeyword(String keyword);

	List<OptionSearchDTO> searchOptionDTOByKeyword(String keyword);

	public Object getOptionById(Long id);

	public List<OptionsDTO> getListAllJoinOptions();

	public List<Images> getImagesByOptionId(Long id);

	public Object getListAllOptionImagePage(Pageable pageable);

	public Object getAllOptionByServiceId(Long id, Pageable pageable);

	public List<OptionsDTO> getListViewAllOption();

	Page<List<Map<String, Object>>> ViewOptionAtHomePage(Pageable pageable);

	List<Map<String, Object>> getDetailOneOptionById(Long id);
	
	public void deleteOption(Long id);
}
