package com.capstone.bhs.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.capstone.bhs.model.dto.QueryAllBookingsDTO;
import com.capstone.bhs.model.dto.UserBookingDTO;
import com.capstone.bhs.model.entity.Booking;
import com.capstone.bhs.model.vm.CreateBookingVM;
import com.capstone.bhs.model.vm.UpdateBookingUserVM;

public interface BookingService {
	public Object getBookingById(Long id);
	
	public Object getBookingByUserId(Long userId);

	public Object getBookingOptionByBookingId(Long id);

	public List<QueryAllBookingsDTO> getAllListBookings();

	public List<QueryAllBookingsDTO> getAllListBookingAdmin();

	public Booking createBooking(CreateBookingVM createBookingVM);
	
	public Booking createBookingUser(CreateBookingVM createBookingVM);

	public List<QueryAllBookingsDTO> getAllListViewBooking();

	public Optional<Booking> updateBooking(CreateBookingVM createBookingVM, Long id);

	void deleteBooking(long[] ids);
	
	void deleteBookingOptionMapping(long[] ids);
	
	List<Map<String, Object>> getBookingByBookingId(Long bookingId);
	
	public Optional<Booking> userUpdateBooking(UpdateBookingUserVM updateBookingUserVM, Long id);
	
	public Object getUserBookingByUserId(Long userId);
	
	Object getBookingStatusToBooking(Long id, String date, String startTime);
	
	public List<UserBookingDTO> findOneByDateTimeAndStartTime(String dateTime, String startTime);
}
