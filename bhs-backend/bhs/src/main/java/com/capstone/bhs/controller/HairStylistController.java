package com.capstone.bhs.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.capstone.bhs.model.dto.HairStylistDTO;
import com.capstone.bhs.model.entity.HairStylist;
import com.capstone.bhs.model.vm.HairStylistVM;
import com.capstone.bhs.repository.BookingRepository;
import com.capstone.bhs.service.HairStylistService;

@RestController
@RequestMapping("/api")
public class HairStylistController {
	@Autowired
	private HairStylistService hairStylistService;

	@GetMapping("/get-all-list-hair-stylists")
	public ResponseEntity<List<HairStylist>> getAllHairStylist() {
		return ResponseEntity.ok().body(hairStylistService.getAllHairStylist());
	}

	@GetMapping("/get-hair-stylist/{id}")
	public ResponseEntity<HairStylist> getHairStylistById(@PathVariable("id") Long id) {
		Optional<HairStylist> obj = hairStylistService.getHairStylistById(id);
		if (obj.isPresent()) {
			return ResponseEntity.ok().body(obj.get());
		} else {
			return ResponseEntity.badRequest().headers(
					HeaderUtil.createFailureAlert("HairStylist", "HairStylist not found", "HairStylist not found"))
					.body(null);
		}

	}

	@PostMapping("/admin/create-hair-stylist")
	public ResponseEntity<?> createHairStylist(@RequestBody HairStylistVM hairStylistVM) {
		try {
			String username = SpringSecurityUtils.getCurrentUserLogin().get();
			hairStylistVM.setCreatedBy(username);
			hairStylistVM.setModifiedBy(username);
			return ResponseEntity.ok().headers(HeaderUtil.createAlert("Create success", "Create success"))
					.body(hairStylistService.createHairStylist(hairStylistVM));
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("HairStylist", "HairStylist Existed", "HairStylist Existed"))
					.body(null);
		}
	}

	@PutMapping("/admin/update-hair-stylist/{id}")
	public ResponseEntity<?> updateHairStylist(@RequestBody HairStylistVM hairStylistVM, @PathVariable("id") long id) {
		try {
			String username = SpringSecurityUtils.getCurrentUserLogin().get();
			hairStylistVM.setCreatedBy(username);
			hairStylistVM.setModifiedBy(username);
			return ResponseEntity.ok().headers(HeaderUtil.createAlert("Update success", "Update success"))
					.body(hairStylistService.updateHairStylist(hairStylistVM, id));
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("HairStylist", "Update failed", "Updated fail")).body(null);
		}
	}

	@PostMapping("/admin/delete-hair-stylist")
	public void deleteStylist(@RequestBody long[] ids) {
		hairStylistService.delete(ids);
		ResponseEntity.ok().headers(HeaderUtil.createAlert("Delete success", "Delete success"));
	}

	@GetMapping("/admin/get-stylist-schedule/{id}")
	public Object getStylishScheduleByStylishId(@PathVariable("id") Long id) {
		try {
			return hairStylistService.getStylishScheduleByStylishId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/user-get-stylist-schedule-booking-by-stylist-id/{id}/{date}")
	public Object getStylishScheduleBookingByStylishId(@PathVariable("id") Long id,
			@PathVariable("date") String dateTime) {
		try {
			return hairStylistService.getStylishScheduleBookingByStylishId(id, dateTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/get-stylist-schedule-booking-by-stylist-id/{id}")
	public Object getOneStylishScheduleBookingByStylishId(@PathVariable("id") Long id) {
		try {
			return hairStylistService.getOneStylishScheduleBookingByStylishId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/get-all-list-stylist-schedule")
	public ResponseEntity<List<HairStylistDTO>> getListAllStylistSchedule() {
		List<HairStylistDTO> lstData = hairStylistService.getLstAllHairStylistSchedule();
		return ResponseEntity.ok().body(lstData);
	}

	@Autowired
	BookingRepository bookingRepository;

	@PostMapping("/admin/delete-hair-stylist-by-id/{id}")
	public ResponseEntity<?> deleteOptionById(@PathVariable("id") Long id) {
		try {
			if (getHairStylistInBooking(id) == null || getHairStylistInBooking(id).isEmpty()) {
				hairStylistService.deleteHairStylist(id);
				return ResponseEntity.ok().headers(HeaderUtil.createAlert("Delete success", "Delete success"))
						.body(null);
			}
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("HairStylist",
							"Can not delete! This HairStylist already on booking",
							"Can not delete! This HairStylist already on booking"))
					.body(null);
		} catch (Exception e) {
			return ResponseEntity.badRequest().headers(
					HeaderUtil.createFailureAlert("HairStylist", "HairStylist not exist", "HairStylist not exist"))
					.body(null);
		}
	}

	@SuppressWarnings("rawtypes")
	private CommonFunction commonFunction = new CommonFunction();

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getHairStylistInBooking(Long id) {
		List<Map<String, Object>> queryData = bookingRepository.getHairStylistInBooking(id);
		return commonFunction.convertListQueryResultToDTO(queryData);
	}

	@GetMapping("/get-all-stylists")
	public Object getAllStylist() {
		try {
			return hairStylistService.getAllStylist();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
