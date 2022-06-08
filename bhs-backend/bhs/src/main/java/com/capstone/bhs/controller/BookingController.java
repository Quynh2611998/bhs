package com.capstone.bhs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.capstone.bhs.model.dto.QueryAllBookingsDTO;
import com.capstone.bhs.model.vm.CreateBookingVM;
import com.capstone.bhs.model.vm.UpdateBookingUserVM;
import com.capstone.bhs.service.BookingService;

@RestController
@RequestMapping("/api")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@GetMapping("/get-booking/{id}")
	public Object getBookingById(@PathVariable("id") Long id) {
		try {
			return bookingService.getBookingById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/get-booking-by-user-id/{user_id}")
	public Object getBookingByUserId(@PathVariable("user_id") Long userId) {
		try {
			return bookingService.getBookingByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/get-booking-option-mapping-by-booking-id/{id}")
	public Object getBookingOptionByBookingId(@PathVariable("id") Long id) {
		try {
			return bookingService.getBookingOptionByBookingId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/admin/get-all-booking")
	public ResponseEntity<List<QueryAllBookingsDTO>> getAllListBookings() {
		List<QueryAllBookingsDTO> lstData = bookingService.getAllListBookings();
		return ResponseEntity.ok().body(lstData);
	}

	@GetMapping("/get-all-list-view-booking")
	public ResponseEntity<List<QueryAllBookingsDTO>> getAllListViewBooking() {
		List<QueryAllBookingsDTO> lstData = bookingService.getAllListViewBooking();
		return ResponseEntity.ok().body(lstData);
	}

	@GetMapping("/admin/get-all-booking-admin")
	public ResponseEntity<List<QueryAllBookingsDTO>> getAllListBookingAdmin() {
		List<QueryAllBookingsDTO> lstData = bookingService.getAllListBookingAdmin();
		return ResponseEntity.ok().body(lstData);
	}

	@PostMapping("/admin/create-booking")
	public ResponseEntity<?> createBooking(@RequestBody CreateBookingVM createBookingVM) {
		try {
			String username = SpringSecurityUtils.getCurrentUserLogin().get();

			if (createBookingVM.getStartTime() == null || createBookingVM.getStartTime().trim().isEmpty()) {
				return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("Booking",
						"Booking Failed, Please check your time slot!", "Booking Failed, Please check your time slot!"))
						.body(null);
			}

			if (createBookingVM.getOptionBookingMappingId() == null
					|| createBookingVM.getOptionBookingMappingId().isEmpty()) {
				return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("Booking",
						"Booking Failed, Please check your option!", "Booking Failed, Please check your option!"))
						.body(null);
			}

			createBookingVM.setCreatedBy(username);
			createBookingVM.setModifiedBy(username);
			return ResponseEntity.ok().headers(HeaderUtil.createAlert("Booking success", "Booking success"))
					.body(bookingService.createBooking(createBookingVM));

		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.headers(
							HeaderUtil.createFailureAlert("Booking", "Booking Failed, Please check your booking again!",
									"Booking Failed, Please check your booking again!"))
					.body(null);
		}
	}

	@PostMapping("/user-create-booking")
	public ResponseEntity<?> createBookingUser(@RequestBody CreateBookingVM createBookingVM) {
		try {
			String username = SpringSecurityUtils.getCurrentUserLogin().get();

			if (createBookingVM.getStartTime() == null || createBookingVM.getStartTime().trim().isEmpty()) {
				return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("Booking",
						"Booking Failed, Please check your time slot!", "Booking Failed, Please check your time slot!"))
						.body(null);
			}

			if (!bookingService
					.findOneByDateTimeAndStartTime(createBookingVM.getDateTime(), createBookingVM.getStartTime())
					.isEmpty()) {
				return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("Booking",
						"Booking Failed, This time slot is existed!", "Booking Failed, This time slot is existed!"))
						.body(null);
			}

			if (bookingService.getBookingStatusToBooking(createBookingVM.getAdUserId(), createBookingVM.getDateTime(),
					createBookingVM.getStartTime()) == null) {
				return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("Booking",
						"Booking Failed, You have a booking aready!", "Booking Failed, You have a booking aready!"))
						.body(null);
			}

			if (createBookingVM.getOptionBookingMappingId() == null
					|| createBookingVM.getOptionBookingMappingId().isEmpty()) {
				return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("Booking",
						"Booking Failed, Please check your option!", "Booking Failed, Please check your option!"))
						.body(null);
			}

			createBookingVM.setCreatedBy(username);
			createBookingVM.setModifiedBy(username);
			return ResponseEntity.ok().headers(HeaderUtil.createAlert("Booking success", "Booking success"))
					.body(bookingService.createBookingUser(createBookingVM));

		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.headers(
							HeaderUtil.createFailureAlert("Booking", "Booking Failed, Please check your booking again!",
									"Booking Failed, Please check your booking again!"))
					.body(null);
		}
	}

	@PutMapping("/admin/update-booking/{id}")
	public ResponseEntity<?> updateBooking(@RequestBody CreateBookingVM createBookingVM, @PathVariable("id") long id) {
		try {

			String username = SpringSecurityUtils.getCurrentUserLogin().get();
			createBookingVM.setModifiedBy(username);
			return ResponseEntity.ok()
					.headers(HeaderUtil.createAlert("Update booking success", "Update booking success"))
					.body(bookingService.updateBooking(createBookingVM, id));

		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("Booking", "Update booking fail", "Update booking fail"))
					.body(null);
		}
	}

	@PostMapping("/delete-booking")
	public void deleteBooking(@RequestBody long[] ids) {
		bookingService.deleteBooking(ids);
		ResponseEntity.ok().headers(HeaderUtil.createAlert("Delete success", "Delete success"));
	}

	@PostMapping("/delete-booking-option-mapping")
	public void deleteBookingOptionMapping(@RequestBody long[] ids) {
		bookingService.deleteBookingOptionMapping(ids);
		ResponseEntity.ok().headers(HeaderUtil.createAlert("Delete success", "Delete success"));
	}

	@GetMapping("/get-booking-by-booking-id/{id}")
	public ResponseEntity<?> getBookingByBookingId(@PathVariable("id") Long bookingId) {
		List<Map<String, Object>> lstData = bookingService.getBookingByBookingId(bookingId);
		return ResponseEntity.ok().body(lstData);
	}

	@PutMapping("/user-update-booking/{id}")
	public ResponseEntity<?> userUpdateBooking(UpdateBookingUserVM updateBookingUserVM, @PathVariable("id") long id) {
		try {
			return ResponseEntity.ok()
					.headers(HeaderUtil.createAlert("Update booking success", "Update booking success"))
					.body(bookingService.userUpdateBooking(updateBookingUserVM, id));

		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("Booking", "Update booking fail", "Update booking fail"))
					.body(null);
		}
	}

	@GetMapping("/user-get-booking-by-user-id/{user_id}")
	public Object getOptionAndCategoryAndService(@PathVariable("user_id") Long userId) {
		try {
			return bookingService.getUserBookingByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	@Autowired
//	BookingRepository bookingRepository;
//
//	@Scheduled(cron = "0 49 23 ? * *")
//	public void scheduleTaskUsingCronExpression() throws InterruptedException {
//		Booking booking = new Booking();
//		if (booking.getStatus().equalsIgnoreCase("booked")) {
//			booking.setStatus("cancelled");
//			bookingRepository.save(booking);
//		}
//		System.out.println("Cancel Success - " + new Date());
//	}

}
