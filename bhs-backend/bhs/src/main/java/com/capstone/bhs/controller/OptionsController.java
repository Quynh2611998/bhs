package com.capstone.bhs.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.bhs.common.CommonFunction;
import com.capstone.bhs.common.HeaderUtil;
import com.capstone.bhs.common.SpringSecurityUtils;
import com.capstone.bhs.model.dto.ImagesDTO;
import com.capstone.bhs.model.dto.OptionsDTO;
import com.capstone.bhs.model.entity.Booking;
import com.capstone.bhs.model.entity.Images;
import com.capstone.bhs.model.entity.Options;
import com.capstone.bhs.model.vm.ImagesVM;
import com.capstone.bhs.model.vm.OptionsVM;
import com.capstone.bhs.repository.BookingRepository;
import com.capstone.bhs.service.ImagesService;
import com.capstone.bhs.service.OptionsService;

@RestController
@RequestMapping("/api")
public class OptionsController {

	@Autowired
	private OptionsService optionsService;

	@Autowired
	private ImagesService imagesService;

	@GetMapping("/get-all-images-option")
	public ResponseEntity<List<Images>> getAllImagesOption() {
		return ResponseEntity.ok().body(imagesService.getAllImages());
	}

//	@GetMapping("/get-all-list-option")
//	public ResponseEntity<List<OptionsDTO>> getListAllJoinOptions() {
//		List<OptionsDTO> lstData = optionsService.getListAllJoinOptions();
//		return ResponseEntity.ok().body(lstData);
//	}

	@GetMapping("/get-all-list-image-name-option")
	public ResponseEntity<List<ImagesDTO>> getListAllOptionImage() {
		List<ImagesDTO> lstData = imagesService.getListAllOptionImage();
		return ResponseEntity.ok().body(lstData);
	}

	@GetMapping("/get-all-list-option")
	public ResponseEntity<?> getListAllOptionAndImage(Pageable pageable) {
		Object lstData = optionsService.getListAllOptionImagePage(pageable);
		return ResponseEntity.ok().body(lstData);
	}

	@GetMapping("/get-all-list-option_admin")
	public ResponseEntity<List<OptionsDTO>> getListViewAllOption() {
		List<OptionsDTO> lstData = optionsService.getListViewAllOption();
		return ResponseEntity.ok().body(lstData);
	}

	@GetMapping("/get-all-option-by-service-id/{id}")
	public ResponseEntity<?> getAllOptionByServiceId(@PathVariable("id") Long id, Pageable pageable) {
		Object lstData = optionsService.getAllOptionByServiceId(id, pageable);
		return ResponseEntity.ok().body(lstData);
	}

	@GetMapping("/get-option/{id}")
	public ResponseEntity<?> getOptionId(@PathVariable("id") Long id) {
		Object obj = optionsService.getOptionById(id);
			return ResponseEntity.ok().body(obj);
	
	}

	@PostMapping("/admin/save-image-option")
	public ResponseEntity<?> createImageOption(@RequestBody ImagesVM imagesVM) {
		try {
			String username = SpringSecurityUtils.getCurrentUserLogin().get();
			if (imagesVM.getSrcImage() != null && imagesVM.getAltImage() != null) {
				imagesVM.setCreatedBy(username);
				imagesVM.setModifiedBy(username);
				imagesService.saveImages(imagesVM);
				return ResponseEntity.ok().headers(HeaderUtil.createAlert("Save image success", "Save image success"))
						.body(null);
			}
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("Images", "Save fail", "Save fail")).body(null);

		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("Images", "Save fail", "Save fail")).body(null);
		}
	}

	/*
	 * @GetMapping("/get-all-list-option") public ResponseEntity<List<Options>>
	 * getAllListOption() { return
	 * ResponseEntity.ok().body(optionsService.getAllListOptions()); }
	 */

	@PostMapping("/admin/create-option")
	public ResponseEntity<?> createOption(@RequestBody OptionsVM optionVM) {
		try {
			String username = SpringSecurityUtils.getCurrentUserLogin().get();
			optionVM.setCreatedBy(username);
			optionVM.setModifiedBy(username);
			return ResponseEntity.ok().headers(HeaderUtil.createAlert("Create success", "Create success"))
					.body(optionsService.createOption(optionVM));
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("Options", "Option Existed", "Option Existed")).body(null);
		}
	}

	@PutMapping("/admin/update-option/{id}")
	public ResponseEntity<?> updateOption(@RequestBody OptionsVM optionVM, @PathVariable("id") long id) {
		try {
			String username = SpringSecurityUtils.getCurrentUserLogin().get();
			optionVM.setCreatedBy(username);
			optionVM.setModifiedBy(username);
			return ResponseEntity.ok().headers(HeaderUtil.createAlert("Update success", "Update success"))
					.body(optionsService.updateOption(optionVM, id));
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("Options", "Update failed", "Updated fail")).body(null);
		}
	}

	@Autowired
	BookingRepository bookingRepository;

	@PostMapping("/admin/delete-option-by-id/{id}")
	public ResponseEntity<?> deleteOptionById(@PathVariable("id") Long id) {
		try {
			if (getOptionInBooking(id) == null || getOptionInBooking(id).isEmpty()) {
				optionsService.deleteOption(id);
				return ResponseEntity.ok().headers(HeaderUtil.createAlert("Delete success", "Delete success"))
						.body(null);
			}
			return ResponseEntity
					.badRequest().headers(HeaderUtil.createFailureAlert("Options",
							"Can not delete! Option already on booking", "Can not delete! Option already on booking"))
					.body(null);
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("Options", "Option not exist", "Option not exist"))
					.body(null);
		}
	}

	@SuppressWarnings("rawtypes")
	private CommonFunction commonFunction = new CommonFunction();

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOptionInBooking(Long id) {
		List<Map<String, Object>> queryData = bookingRepository.getOptionInBooking(id);
		return commonFunction.convertListQueryResultToDTO(queryData);
	}

	@PostMapping("/admin/delete-option")
	public void deleteOption(@RequestBody long[] ids) {

		optionsService.delete(ids);
		ResponseEntity.ok().headers(HeaderUtil.createAlert("Delete success", "Delete success"));
	}

	@GetMapping("/search-option-by-keyword")
	public ResponseEntity<?> searchOptionByKeyword(@RequestParam("keyword") String keyword) {
		List<Map<String, Object>> lstData = optionsService.searchOptionByKeyword(keyword);
//		List<OptionSearchDTO> lstData = optionsService.searchOptionDTOByKeyword(keyword);
		return ResponseEntity.ok().body(lstData);
	}

	@GetMapping("/get-images-by-option-id/{id}")
	public ResponseEntity<List<Images>> getImagesByOptionId(@PathVariable("id") Long id) {
		List<Images> image = optionsService.getImagesByOptionId(id);
		if (image.isEmpty())
			return ResponseEntity.ok().body(null);
		return ResponseEntity.ok().body(image);
	}

	@PostMapping("/admin/delete-images")
	public void deleteImages(@RequestBody long[] ids) {
		optionsService.deleteImages(ids);
		ResponseEntity.ok().headers(HeaderUtil.createAlert("Delete success", "Delete success"));
	}

	@GetMapping("/view-option-at-home-page")
	public ResponseEntity<?> ViewOptionAtHomePage(Pageable pageable) {
		Page<List<Map<String, Object>>> lstData = optionsService.ViewOptionAtHomePage(pageable);
		return ResponseEntity.ok().body(lstData);
	}

	@GetMapping("/get-detail-option-by-id/{id}")
	public ResponseEntity<?> getDetailOneOptionById(@PathVariable("id") Long id) {
		List<Map<String, Object>> lstData = optionsService.getDetailOneOptionById(id);
		if (lstData.isEmpty())
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("Options", "Not exist", "Not exist")).body(null);
		return ResponseEntity.ok().body(lstData);
	}

}
