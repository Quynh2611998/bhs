package com.capstone.bhs.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.bhs.common.CommonFunction;
import com.capstone.bhs.common.HeaderUtil;
import com.capstone.bhs.common.SpringSecurityUtils;
import com.capstone.bhs.model.dto.ServicesDTO;
import com.capstone.bhs.model.entity.Services;
import com.capstone.bhs.model.vm.ServicesVM;
import com.capstone.bhs.repository.BookingRepository;
import com.capstone.bhs.service.ServicesService;

@RestController
@RequestMapping("/api")

public class ServiceController {

	@Autowired
	private ServicesService servicesService;

	@GetMapping("/get-service/{id}")
	public ResponseEntity<Services> getServiceById(@PathVariable("id") Long id) {
		Optional<Services> obj = servicesService.getServiceById(id);
		if (obj.isPresent()) {
			return ResponseEntity.ok().body(obj.get());
		} else {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("Services", "Service not found", "Service not found"))
					.body(null);
		}
	}

	@GetMapping("/get-all-service-by-category-id/{id}")
	public ResponseEntity<?> getAllOptionByServiceId(@PathVariable("id") Long id, Pageable pageable) {
		Object lstData = servicesService.getAllServiceByCategoryId(id, pageable);
		return ResponseEntity.ok().body(lstData);
	}

	@GetMapping("/get-all-list-services")
	public ResponseEntity<?> getListAllJoinServices() {
		List<ServicesDTO> lstData = servicesService.getListAllJoinServices();
		return ResponseEntity.ok().body(lstData);
	}

	/*
	 * @GetMapping("/get-all-list-services") public ResponseEntity<List<Services>>
	 * getAllListServices() { return
	 * ResponseEntity.ok().body(servicesService.getAllListServices()); }
	 */

	@PostMapping("/admin/create-service")
	public ResponseEntity<?> createService(@RequestBody ServicesVM servicesVM) {
		try {
			String username = SpringSecurityUtils.getCurrentUserLogin().get();
			servicesVM.setCreatedBy(username);
			servicesVM.setModifiedBy(username);
			return ResponseEntity.ok().headers(HeaderUtil.createAlert("Create success", "Create success"))
					.body(servicesService.createServices(servicesVM));

		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("Services", "Service Existed", "Service Existed"))
					.body(null);
		}
	}

	@PutMapping("/admin/update-service/{id}")
	public ResponseEntity<?> updateService(@RequestBody ServicesVM servicesVM, @PathVariable("id") long id) {
		try {
			String username = SpringSecurityUtils.getCurrentUserLogin().get();
			servicesVM.setCreatedBy(username);
			servicesVM.setModifiedBy(username);
			return ResponseEntity.ok().headers(HeaderUtil.createAlert("Update success", "Update success"))
					.body(servicesService.updateServices(servicesVM, id));
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("Services", "Update failed", "Updated fail")).body(null);
		}
	}

	@PostMapping("/admin/delete-service")
	public void deleteService(@RequestBody long[] ids) {
		servicesService.delete(ids);
		ResponseEntity.ok().headers(HeaderUtil.createAlert("Delete success", "Delete success"));
	}

	@GetMapping("/get-object-home-page")
	public Object getOptionAndCategoryAndService() {
		try {
			return servicesService.getOptionAndCategoryAndService();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Autowired
	BookingRepository bookingRepository;

	@PostMapping("/admin/delete-service-by-id/{id}")
	public ResponseEntity<?> deleteOptionById(@PathVariable("id") Long id) {
		try {
			if (getServiceInBooking(id) == null || getServiceInBooking(id).isEmpty()) {
				servicesService.deleteService(id);
				return ResponseEntity.ok().headers(HeaderUtil.createAlert("Delete success", "Delete success"))
						.body(null);
			}
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("Service",
							"Can not delete! Option of this Service already on booking",
							"Can not delete! Option of this Service already on booking"))
					.body(null);
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("Service", "Service not exist", "Service not exist"))
					.body(null);
		}
	}

	@SuppressWarnings("rawtypes")
	private CommonFunction commonFunction = new CommonFunction();

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getServiceInBooking(Long id) {
		List<Map<String, Object>> queryData = bookingRepository.getServiceInBooking(id);
		return commonFunction.convertListQueryResultToDTO(queryData);
	}
}