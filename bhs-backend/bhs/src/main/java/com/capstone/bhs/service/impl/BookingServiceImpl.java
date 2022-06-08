package com.capstone.bhs.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.bhs.common.CommonFunction;
import com.capstone.bhs.model.dto.BookingIdListAndDetailDTO;
import com.capstone.bhs.model.dto.DateTimeBookingListAndDetailDTO;
import com.capstone.bhs.model.dto.OptionBookingListAndDetailDTO;
import com.capstone.bhs.model.dto.QueryAllBookingsDTO;
import com.capstone.bhs.model.dto.QueryAllDTO;
import com.capstone.bhs.model.dto.QueryBookingDTO;
import com.capstone.bhs.model.dto.QueryUserBookingDTO;
import com.capstone.bhs.model.dto.UserBookingDTO;
import com.capstone.bhs.model.entity.Booking;
import com.capstone.bhs.model.entity.BookingOptionMapping;
import com.capstone.bhs.model.vm.CreateBookingVM;
import com.capstone.bhs.model.vm.UpdateBookingUserVM;
import com.capstone.bhs.repository.BookingOptionMappingRepository;
import com.capstone.bhs.repository.BookingRepository;
import com.capstone.bhs.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

@Service
public class BookingServiceImpl implements BookingService {

//	@Autowired
//	private AdUserRepository adUserRepository;

	@SuppressWarnings("rawtypes")
	private CommonFunction commonFunction = new CommonFunction();
	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BookingOptionMappingRepository bookingOptionMappingRepository;
//	@Autowired
//	private OptionsRepository optionsRepository;
//	@Autowired
//	private HairStylistRepository hairStylistRepository;
//	@Autowired
//	private VoucherOptionRepository voucherOptionRepository;
//	@Autowired
//	private VoucherUserRepository voucherUserRepository;

	@Override
	public Object getBookingById(Long id) {
		List<Map<String, Object>> queryData = bookingRepository.getBookingById(id);
		List<QueryBookingDTO> result = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper().registerModule(new ParameterNamesModule())
				.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
		for (Map<String, Object> res : queryData) {
			QueryBookingDTO obj = mapper.convertValue(res, QueryBookingDTO.class);
			result.add(obj);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QueryAllBookingsDTO> getAllListBookings() {
		List<Map<String, Object>> queryData = bookingRepository.getAllListBooking();
		return commonFunction.convertListQueryResultToDTO(queryData);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QueryAllBookingsDTO> getAllListViewBooking() {
		List<Map<String, Object>> queryData = bookingRepository.getAllListViewBooking();
		return commonFunction.convertListQueryResultToDTO(queryData);
	}

	@Override
	public Booking createBooking(CreateBookingVM createBookingVM) {
		Booking saveBooking = new Booking();

		Optional<Booking> id = bookingRepository.findOneById(createBookingVM.getId());
		if (id.isPresent()) {
			throw new RuntimeErrorException(null);
		}

		saveBooking.setUserProfileId(createBookingVM.getUserProfileId());
		saveBooking.setHairStylistId(createBookingVM.getHairStylistId());
		saveBooking.setPrice(createBookingVM.getTotalPrice());
		saveBooking.setTotalDuration(createBookingVM.getTotalDuration());
		saveBooking.setVoucherOptionId(createBookingVM.getVoucherOptionId());
		saveBooking.setVoucherUserId(createBookingVM.getVoucherUserId());
		saveBooking.setPhoneNumber(createBookingVM.getPhoneNumer());
		saveBooking.setDateTime(createBookingVM.getDateTime());
		saveBooking.setStatus(createBookingVM.getStatus());
		saveBooking.setNote(createBookingVM.getNote());
		saveBooking.setStartTime(createBookingVM.getStartTime());
		saveBooking.setEndTime(createBookingVM.getEndTime());
		saveBooking.setCreatedDate(Instant.now());
		saveBooking.setCreatedBy(createBookingVM.getCreatedBy());
		saveBooking.setModifiedDate(Instant.now());
		saveBooking.setModifiedBy(createBookingVM.getModifiedBy());
		bookingRepository.save(saveBooking);

		for (Long getOptionId : createBookingVM.getOptionBookingMappingId()) {
			BookingOptionMapping bookingOptionMapping = new BookingOptionMapping();
			bookingOptionMapping.setBookingId(saveBooking.getId());
			bookingOptionMapping.setOptionId(getOptionId);
			bookingOptionMappingRepository.save(bookingOptionMapping);
		}
		return saveBooking;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QueryAllBookingsDTO> getAllListBookingAdmin() {
		List<Map<String, Object>> queryData = bookingRepository.getAllListBookingAdmin();
		return commonFunction.convertListQueryResultToDTO(queryData);
	}

	@Override
	public Object getBookingOptionByBookingId(Long id) {
		List<Map<String, Object>> queryData = bookingRepository.getBookingOptionByBookingId(id);
		List<QueryBookingDTO> result = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper().registerModule(new ParameterNamesModule())
				.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
		for (Map<String, Object> res : queryData) {
			QueryBookingDTO obj = mapper.convertValue(res, QueryBookingDTO.class);
			result.add(obj);
		}
		return result;
	}

	@Override
	public Optional<Booking> updateBooking(CreateBookingVM createBookingVM, Long id) {
		return bookingRepository.findOneById(id).map(booking -> {
			booking.setUserProfileId(createBookingVM.getUserProfileId());
			booking.setHairStylistId(createBookingVM.getHairStylistId());
			booking.setPrice(createBookingVM.getTotalPrice());
			booking.setPhoneNumber(createBookingVM.getPhoneNumer());
			booking.setTotalDuration(createBookingVM.getTotalDuration());
			booking.setVoucherOptionId(createBookingVM.getVoucherOptionId());
			booking.setVoucherUserId(createBookingVM.getVoucherUserId());
			booking.setDateTime(createBookingVM.getDateTime());
			booking.setStatus(createBookingVM.getStatus());
			booking.setNote(createBookingVM.getNote());
			booking.setStartTime(createBookingVM.getStartTime());
			booking.setEndTime(createBookingVM.getEndTime());
			booking.setCreatedDate(Instant.now());
			booking.setCreatedBy(createBookingVM.getCreatedBy());
			booking.setModifiedDate(Instant.now());
			booking.setModifiedBy(createBookingVM.getModifiedBy());
			bookingRepository.save(booking);

			for (Long getOptionId : createBookingVM.getOptionBookingMappingId()) {

				BookingOptionMapping bookingOptionMapping = new BookingOptionMapping();
				bookingOptionMapping.setBookingId(booking.getId());
				bookingOptionMapping.setOptionId(getOptionId);
				bookingOptionMappingRepository.save(bookingOptionMapping);

			}

			return booking;
		});
	}

	@Override
	public void deleteBooking(long[] ids) {
		for (long item : ids) {
			bookingRepository.deleteById(item);
		}

	}

	@Override
	public void deleteBookingOptionMapping(long[] ids) {
		for (long item : ids) {
			bookingOptionMappingRepository.deleteById(item);
		}

	}

	/*
	 * =============================================================================
	 */

	@Override
	public Booking createBookingUser(CreateBookingVM createBookingVM) {
		Booking saveBooking = new Booking();

		Optional<Booking> id = bookingRepository.findOneById(createBookingVM.getId());
		if (id.isPresent()) {
			throw new RuntimeErrorException(null);
		}
		if ((findOneByDateTimeAndStartTime(createBookingVM.getDateTime(), createBookingVM.getStartTime()).isEmpty())
				&& (getBookingStatusToBooking(createBookingVM.getAdUserId(), createBookingVM.getDateTime(),
						createBookingVM.getStartTime()) != null)) {

			saveBooking.setUserProfileId(createBookingVM.getUserProfileId());
			saveBooking.setHairStylistId(createBookingVM.getHairStylistId());
			saveBooking.setPrice(createBookingVM.getTotalPrice());
			saveBooking.setTotalDuration(createBookingVM.getTotalDuration());
			saveBooking.setVoucherOptionId(createBookingVM.getVoucherOptionId());
			saveBooking.setVoucherUserId(createBookingVM.getVoucherUserId());
			saveBooking.setPhoneNumber(createBookingVM.getPhoneNumer());
			saveBooking.setDateTime(createBookingVM.getDateTime());
			saveBooking.setStatus(createBookingVM.getStatus());
			saveBooking.setNote(createBookingVM.getNote());
			saveBooking.setStartTime(createBookingVM.getStartTime());
			saveBooking.setEndTime(createBookingVM.getEndTime());
			saveBooking.setCreatedDate(Instant.now());
			saveBooking.setCreatedBy(createBookingVM.getCreatedBy());
			saveBooking.setModifiedDate(Instant.now());
			saveBooking.setModifiedBy(createBookingVM.getModifiedBy());
			bookingRepository.save(saveBooking);
			for (Long getOptionId : createBookingVM.getOptionBookingMappingId()) {
				BookingOptionMapping bookingOptionMapping = new BookingOptionMapping();
				bookingOptionMapping.setBookingId(saveBooking.getId());
				bookingOptionMapping.setOptionId(getOptionId);
				bookingOptionMappingRepository.save(bookingOptionMapping);
			}
		} else {
			throw new RuntimeErrorException(null);
		}
		return saveBooking;
	}

	@Override
	public Object getBookingByUserId(Long userId) {
		List<Map<String, Object>> queryData = bookingRepository.getBookingByUserId(userId);
		List<QueryBookingDTO> result = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper().registerModule(new ParameterNamesModule())
				.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
		for (Map<String, Object> res : queryData) {
			QueryBookingDTO obj = mapper.convertValue(res, QueryBookingDTO.class);
			result.add(obj);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getBookingByBookingId(Long bookingId) {
		return bookingRepository.getBookingByBookingId(bookingId);
	}

	@Override
	public List<UserBookingDTO> findOneByDateTimeAndStartTime(String dateTime, String startTime) {
		List<Map<String, Object>> queryData = bookingRepository.findOneByDateTimeAndStartTime(dateTime, startTime);
		List<UserBookingDTO> result = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper().registerModule(new ParameterNamesModule())
				.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
		for (Map<String, Object> res : queryData) {
			UserBookingDTO obj = mapper.convertValue(res, UserBookingDTO.class);
			result.add(obj);
		}
		return result;
	}

	@Override
	public Optional<Booking> userUpdateBooking(UpdateBookingUserVM updateBookingUserVM, Long id) {
		return bookingRepository.findOneById(id).map(booking -> {
			String status = "cancelled by user";
			booking.setStatus(status);
			bookingRepository.save(booking);
			return booking;
		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object getUserBookingByUserId(Long userId) {
		List<Map<String, Object>> queryData = bookingRepository.getUserBookingByUserId(userId);
		List<QueryAllDTO> result = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper().registerModule(new ParameterNamesModule())
				.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
		for (Map<String, Object> res : queryData) {
			QueryAllDTO obj = mapper.convertValue(res, QueryAllDTO.class);
			result.add(obj);
		}
		Map<String, Map<String, Map<Long, List<QueryAllDTO>>>> mapUserName = result.stream()
				.collect(Collectors.groupingBy(QueryAllDTO::getUserName, Collectors.groupingBy(QueryAllDTO::getDateTime,
						Collectors.groupingBy(QueryAllDTO::getBookingId))));

		QueryUserBookingDTO res = new QueryUserBookingDTO();
		res.setUserId(userId);
		for (String UserName : mapUserName.keySet()) {
			res.setUserName(UserName);
			res.setDateTime(new ArrayList<DateTimeBookingListAndDetailDTO>());

			for (String dateTime : mapUserName.get(UserName).keySet()) {
				DateTimeBookingListAndDetailDTO s = new DateTimeBookingListAndDetailDTO();
				s.setBooking(new ArrayList<BookingIdListAndDetailDTO>());
				List<BookingIdListAndDetailDTO> lstBookingId = new ArrayList<>();
				// List<QueryAllDTO> all = mapUserName.get(UserName).get(dateTime).keySet();

				for (Long bookingId : mapUserName.get(UserName).get(dateTime).keySet()) {
					BookingIdListAndDetailDTO b = new BookingIdListAndDetailDTO();
					List<OptionBookingListAndDetailDTO> lstOption = new ArrayList<>();
					List<QueryAllDTO> all = mapUserName.get(UserName).get(dateTime).get(bookingId);
					String bookingStatus = "";
					double bookingTotalPrice = 0;
					for (QueryAllDTO obj : all) {
						OptionBookingListAndDetailDTO tmp = new OptionBookingListAndDetailDTO();
						tmp.setOptionId(obj.getOptionId());
						tmp.setOptionName(obj.getOptionName());
						tmp.setPrice(obj.getPrice());
						tmp.setDuration(obj.getDuration());
						tmp.setCreatedDate(obj.getCreatedDate());

						tmp.setTotalDuration(obj.getTotalDuration());

						tmp.setStartTime(obj.getStartTime());
						tmp.setEndTime(obj.getEndTime());
						tmp.setPhoneNumber(obj.getPhoneNumber());
						tmp.setStylistName(obj.getStylistName());
						lstOption.add(tmp);
						bookingStatus = obj.getStatus();
						bookingTotalPrice = obj.getTotalPrice();
					}
					b.setStatus(bookingStatus);
					b.setTotalPrice(bookingTotalPrice);
					s.setDateTime(dateTime);
					b.setBookingId(bookingId);
					b.setOption(lstOption);
					lstBookingId.add(b);
					s.setBooking(lstBookingId);

				}
				res.getDateTime().add(s);
			}
		}
		return res;
	}

	@Override
	public Object getBookingStatusToBooking(Long id, String date, String startTime) {
		List<Map<String, Object>> queryData = bookingRepository.getBookingStatusToBooking(id, date, startTime);
		List<UserBookingDTO> result = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper().registerModule(new ParameterNamesModule())
				.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
		for (Map<String, Object> res : queryData) {
			UserBookingDTO obj = mapper.convertValue(res, UserBookingDTO.class);
			result.add(obj);
		}
		return result;
	}

}
