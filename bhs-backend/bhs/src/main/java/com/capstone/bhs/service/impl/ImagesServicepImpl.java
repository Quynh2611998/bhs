package com.capstone.bhs.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.bhs.common.CommonFunction;
import com.capstone.bhs.model.dto.ImagesDTO;
import com.capstone.bhs.model.entity.Images;
import com.capstone.bhs.model.vm.ImagesVM;
import com.capstone.bhs.repository.ImagesRepository;
import com.capstone.bhs.service.ImagesService;

@Service
public class ImagesServicepImpl implements ImagesService {

	@SuppressWarnings("rawtypes")
	private CommonFunction commonFunction = new CommonFunction();

	@Autowired
	private ImagesRepository imagesRepository;

	@Override
	public List<Images> getAllImages() {
		return imagesRepository.findAll();
	}

	@Override
	public void saveImages(ImagesVM imagesVM) {
		Images images = new Images();
		images.setOptionId(imagesVM.getOptionId());
		images.setSrcImage(imagesVM.getSrcImage());
		images.setAltImage(imagesVM.getAltImage());
		images.setCreatedDate(Instant.now());
		images.setCreatedBy(imagesVM.getCreatedBy());
		images.setModifiedDate(Instant.now());
		images.setModifiedBy(imagesVM.getModifiedBy());
		imagesRepository.save(images);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ImagesDTO> getListAllOptionImage() {
		List<Map<String, Object>> queryData = imagesRepository.lstDetailOptionImage();
		return commonFunction.convertListQueryResultToDTO(queryData);
	}

}
