package com.capstone.bhs.service;

import java.util.List;

import com.capstone.bhs.model.dto.ImagesDTO;
import com.capstone.bhs.model.entity.Images;
import com.capstone.bhs.model.vm.ImagesVM;

public interface ImagesService {

	public List<Images> getAllImages();

	public void saveImages(ImagesVM imagesVM);
	
	public List<ImagesDTO> getListAllOptionImage();
}
