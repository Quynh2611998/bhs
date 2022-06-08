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
import org.springframework.web.bind.annotation.RestController;

import com.capstone.bhs.common.HeaderUtil;
import com.capstone.bhs.common.SpringSecurityUtils;
import com.capstone.bhs.model.dto.VoucherOptionMappingDTO;
import com.capstone.bhs.model.dto.VoucherUserMappingDTO;
import com.capstone.bhs.model.entity.VoucherOptions;
import com.capstone.bhs.model.entity.VoucherUser;
import com.capstone.bhs.model.vm.VoucherOptionsVM;
import com.capstone.bhs.model.vm.VoucherUserVM;
import com.capstone.bhs.repository.VoucherOptionRepository;
import com.capstone.bhs.repository.VoucherUserRepository;
import com.capstone.bhs.service.VoucherOptionMappingService;
import com.capstone.bhs.service.VoucherUserMappingService;

@RestController
@RequestMapping("/api")
public class VoucherController {

	@Autowired
	private VoucherOptionMappingService voucherOptionMappingService;

	@Autowired
	private VoucherUserMappingService voucherUserMappingService;

	@Autowired
	private VoucherOptionRepository voucherOptionRepository;

	@Autowired
	private VoucherUserRepository voucherUserRepository;

	@GetMapping("/get-all-option-by-voucher-option-id/{id}")
	public Object getAllOptionByVoucherOptionId(@PathVariable("id") Long id) {
		try {
			return voucherOptionMappingService.getAllOptionByVoucherOptionId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/get-all-voucher-option-by-option-id/{id}")
	public Object getAllVoucherOptionByOptionId(@PathVariable("id") Long id) {
		try {
			return voucherOptionMappingService.getAllVoucherOptionByOptionId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/get-all-list-voucher-option")
	public ResponseEntity<List<VoucherOptions>> getAllListVoucherOption() {
		return ResponseEntity.ok().body(voucherOptionMappingService.getAllListVoucherOption());
	}

	@GetMapping("/get-one-voucher-option/{id}")
	public ResponseEntity<VoucherOptions> getVoucherOptionId(@PathVariable("id") Long id) {
		Optional<VoucherOptions> obj = voucherOptionMappingService.getVoucherOptionById(id);
		if (obj.isPresent()) {
			return ResponseEntity.ok().body(obj.get());
		} else {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("VoucherOptions",
					"Voucher option not found", "Voucher option not found")).body(null);
		}
	}

	@GetMapping("/get-all-list-voucher-option-mapping")
	public ResponseEntity<?> getAllListVoucherOptionMapping() {
		List<VoucherOptionMappingDTO> lstData = voucherOptionMappingService.getAllListVoucherOptionMapping();
		return ResponseEntity.ok().body(lstData);
	}

	@PostMapping("/admin/create-voucher-option")
	public ResponseEntity<?> createVoucherOption(@RequestBody VoucherOptionsVM voucherOptionsVM) {
		try {
//			List<Long> listOptionId = voucherOptionsVM.getOptionId();
//			if (listOptionId != null && listOptionId.size() == 1 && listOptionId.get(0) == -1) {
//				voucherOptionMappingService.createVoucherOption(voucherOptionsVM);
//			} else {

			String username = SpringSecurityUtils.getCurrentUserLogin().get();
			voucherOptionsVM.setCreatedBy(username);
			voucherOptionsVM.setModifiedBy(username);
			return ResponseEntity.ok()
					.headers(HeaderUtil.createAlert("Create voucher success", "Create voucher success"))
					.body(voucherOptionMappingService.createVoucherOption(voucherOptionsVM));

		} catch (Exception e) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("VoucherOptions",
					"Create voucher options fail", "Create voucher options fail")).body(null);
		}
	}

	@PutMapping("/admin/update-voucher-option/{id}")
	public ResponseEntity<?> updateVoucherOption(@RequestBody VoucherOptionsVM voucherOptionsVM,
			@PathVariable("id") long id) {
		try {

			String username = SpringSecurityUtils.getCurrentUserLogin().get();
			voucherOptionsVM.setModifiedBy(username);
			return ResponseEntity.ok()
					.headers(HeaderUtil.createAlert("Update voucher success", "Update voucher success"))
					.body(voucherOptionMappingService.updateVoucherOption(voucherOptionsVM, id));

		} catch (Exception e) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("VoucherOptions",
					"Update voucher options fail", "Update voucher options fail")).body(null);
		}
	}

	@PostMapping("/admin/delete-voucher-option")
	public void deleteVoucherOption(@RequestBody long[] ids) {
		voucherOptionMappingService.deleteVoucherOption(ids);
		ResponseEntity.ok().headers(HeaderUtil.createAlert("Delete success", "Delete success"));
	}

	@PostMapping("/admin/delete-voucher-option-mapping")
	public void deleteVoucherOptionMapping(@RequestBody long[] ids) {
		voucherOptionMappingService.deleteVoucherOptionMapping(ids);
		ResponseEntity.ok().headers(HeaderUtil.createAlert("Delete success", "Delete success"));
	}

	@PutMapping("/admin/active-voucher-option/{id}")
	public ResponseEntity<?> activeVoucherOption(VoucherOptionsVM voucherOptionsVM, @PathVariable("id") Long id) {
		try {
			Optional<VoucherOptions> actived = voucherOptionRepository.findOneById(id);
			if (actived.get().isActived()) {
				return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("VoucherOptions",
						"Voucher option is actived", "Voucher option is actived")).body(null);
			}
			return ResponseEntity.ok().headers(HeaderUtil.createAlert("Actived success", "Actived success"))
					.body(voucherOptionMappingService.activeVoucherOption(voucherOptionsVM, id));
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("VoucherOptions", "Error", "Error")).body(null);
		}
	}

	/*--------------------------------------------------------------------------------------------*/

	@GetMapping("/get-all-list-voucher-user")
	public ResponseEntity<?> getAllListVoucherUser(Pageable pageable) {
		Page<List<Map<String, Object>>> lstData = voucherUserMappingService.findAllVoucherUser(pageable);
		return ResponseEntity.ok().body(lstData);
	}

	@GetMapping("/get-one-voucher-user/{id}")
	public ResponseEntity<VoucherUser> getVoucherUserId(@PathVariable("id") Long id) {
		Optional<VoucherUser> obj = voucherUserMappingService.getVoucherUserById(id);
		if (obj.isPresent()) {
			return ResponseEntity.ok().body(obj.get());
		} else {
			return ResponseEntity.badRequest().headers(
					HeaderUtil.createFailureAlert("VoucherUser", "Voucher user not found", "Voucher user not found"))
					.body(null);
		}
	}

	@GetMapping("/get-all-list-voucher-user-mapping")
	public ResponseEntity<?> getAllListVoucherUserMapping() {
		List<VoucherUserMappingDTO> lstData = voucherUserMappingService.getAllListVoucherUserMapping();
		return ResponseEntity.ok().body(lstData);
	}

	@PostMapping("/admin/create-voucher-user")
	public ResponseEntity<?> createVoucherUser(@RequestBody VoucherUserVM voucherUserVM) {
		try {
			String username = SpringSecurityUtils.getCurrentUserLogin().get();

			voucherUserVM.setCreatedBy(username);
			voucherUserVM.setModifiedBy(username);
			return ResponseEntity.ok()
					.headers(HeaderUtil.createAlert("Create voucher success", "Create voucher success"))
					.body(voucherUserMappingService.createVoucherUser(voucherUserVM));

		} catch (Exception e) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("VoucherUser",
					"Create voucher user fail", "Create voucher user fail")).body(null);
		}
	}

	@PutMapping("/admin/update-voucher-user/{id}")
	public ResponseEntity<?> updateVoucherUser(@RequestBody VoucherUserVM voucherUserVM, @PathVariable("id") long id) {
		try {
			String username = SpringSecurityUtils.getCurrentUserLogin().get();
			voucherUserVM.setModifiedBy(username);
			return ResponseEntity.ok()
					.headers(HeaderUtil.createAlert("Update voucher success", "Update voucher success"))
					.body(voucherUserMappingService.updateVoucherUser(voucherUserVM, id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("VoucherOptions",
					"Update voucher user fail", "Update voucher user fail")).body(null);
		}
	}

	@PostMapping("/admin/delete-voucher-user")
	public void deleteVoucherUser(@RequestBody long[] ids) {
		voucherUserMappingService.deleteVoucherUser(ids);
		ResponseEntity.ok().headers(HeaderUtil.createAlert("Delete success", "Delete success"));
	}

	@PostMapping("/admin/delete-voucher-user-mapping")
	public void deleteVoucherUserMapping(@RequestBody long[] ids) {
		voucherUserMappingService.deleteVoucherUserMapping(ids);
		ResponseEntity.ok().headers(HeaderUtil.createAlert("Delete success", "Delete success"));
	}

	@PutMapping("/active-voucher-user/{id}")
	public ResponseEntity<?> activeVoucherUser(VoucherUserVM voucherUserVM, @PathVariable("id") Long id) {
		try {
			Optional<VoucherUser> actived = voucherUserRepository.findOneById(id);
			if (actived.get().isActived()) {
				return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("VoucherUser",
						"Voucher user is actived", "Voucher user is actived")).body(null);
			}
			return ResponseEntity.ok().headers(HeaderUtil.createAlert("Actived success", "Actived success"))
					.body(voucherUserMappingService.activeVoucherUser(voucherUserVM, id));
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("VoucherUser",
							"Activation failed! Please check your voucher again",
							"Activation failed! Please check your voucher again"))
					.body(null);
		}
	}

	@GetMapping("/get-one-voucher-user-by-user-id/{id}")
	public ResponseEntity<?> findOneVoucherUserByUserId(@PathVariable("id") Long id) {
		List<Map<String, Object>> lstData = voucherUserMappingService.findOneVoucherUserByUserId(id);
		if (lstData.isEmpty())
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("VoucherUserMapping", "Not exist", "Not exist")).body(null);
		return ResponseEntity.ok().body(lstData);
	}

	@GetMapping("/get-voucher-user-is-actived-by-user-id/{id}")
	public ResponseEntity<?> findVoucherUserIsActived(@PathVariable("id") Long id) {
		List<Map<String, Object>> lstData = voucherUserMappingService.findVoucherUserIsActived(id);
		if (lstData.isEmpty())
			return ResponseEntity.ok().body(null);
		return ResponseEntity.ok().body(lstData);
	}

}
