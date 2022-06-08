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
import com.capstone.bhs.model.dto.HairStylistDTO;
import com.capstone.bhs.model.dto.QueryBookingDTO;
import com.capstone.bhs.model.dto.QueryScheduleDTO;
import com.capstone.bhs.model.dto.SchedulesDTO;
import com.capstone.bhs.model.dto.ShiftsDTO;
import com.capstone.bhs.model.dto.StylistScheduleDTO;
import com.capstone.bhs.model.dto.getStylistDTO;
import com.capstone.bhs.model.entity.HairStylist;
import com.capstone.bhs.model.vm.HairStylistVM;
import com.capstone.bhs.repository.HairStylistRepository;
import com.capstone.bhs.service.HairStylistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

@Service
public class HairStylistServiceImpl implements HairStylistService {

	@Autowired
	private HairStylistRepository hairStylistRepository;

	@SuppressWarnings("rawtypes")
	private CommonFunction commonFunctions = new CommonFunction();

	@SuppressWarnings("rawtypes")
	private CommonFunction<QueryScheduleDTO> commonFunction = new CommonFunction<QueryScheduleDTO>();

	@Override
	public List<HairStylist> getAllHairStylist() {
		return hairStylistRepository.findAll();
	}

	@Override
	public HairStylist createHairStylist(HairStylistVM stylistVM) {
		HairStylist saveObj = new HairStylist();
		Optional<HairStylist> name = hairStylistRepository.findOneBystylistName(stylistVM.getStylistName());
		if (name.isPresent()) {
			throw new RuntimeErrorException(null);
		}
		saveObj.setStylistName(stylistVM.getStylistName());
		saveObj.setGender(stylistVM.getGender());
		saveObj.setPhoneNumber(stylistVM.getPhoneNumber());
		saveObj.setCreatedDate(Instant.now());
		saveObj.setCreatedBy(stylistVM.getCreatedBy());
		saveObj.setModifiedDate(Instant.now());
		saveObj.setModifiedBy(stylistVM.getModifiedBy());
		hairStylistRepository.save(saveObj);
		return saveObj;
	}

	@Override
	public Optional<HairStylist> updateHairStylist(HairStylistVM stylistVM, Long id) {
		return hairStylistRepository.findOneById(id).map(user -> {
			user.setStylistName(stylistVM.getStylistName());
			user.setGender(stylistVM.getGender());
			user.setPhoneNumber(stylistVM.getPhoneNumber());
			user.setCreatedBy(stylistVM.getCreatedBy());
			user.setModifiedBy(stylistVM.getModifiedBy());
			hairStylistRepository.save(user);
			return user;
		});
	}

	@Override
	public void delete(long[] ids) {
		for (long item : ids) {
			hairStylistRepository.deleteById(item);
		}
	}

	@Override
	public Optional<HairStylist> getHairStylistById(Long id) {
		return hairStylistRepository.findById(id);
	}

	@Override
	public Object getStylishScheduleByStylishId(Long id) {
		List<Map<String, Object>> queryData = hairStylistRepository.getStylishScheduleByStylishId(id);
		List<QueryScheduleDTO> result = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper().registerModule(new ParameterNamesModule())
				.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
		for (Map<String, Object> res : queryData) {
			QueryScheduleDTO obj = mapper.convertValue(res, QueryScheduleDTO.class);
			result.add(obj);
		}
		Map<String, Map<String, List<QueryScheduleDTO>>> mapStylistName = result.stream().collect(Collectors
				.groupingBy(QueryScheduleDTO::getStylistName, Collectors.groupingBy(QueryScheduleDTO::getDateTime)));
		StylistScheduleDTO res = new StylistScheduleDTO();
		res.setId(id);
		for (String stylistName : mapStylistName.keySet()) {
			res.setStylistName(stylistName);
			res.setSchedules(new ArrayList<SchedulesDTO>());
			for (String dateTime : mapStylistName.get(stylistName).keySet()) {
				SchedulesDTO s = new SchedulesDTO();
				List<ShiftsDTO> lstShift = new ArrayList<>();
				List<QueryScheduleDTO> shift = mapStylistName.get(stylistName).get(dateTime);
				for (QueryScheduleDTO obj : shift) {
					ShiftsDTO tmp = new ShiftsDTO();
					tmp.setBookingNote(obj.getBookingNote());
					tmp.setCreatedBy(obj.getCreatedBy());
					tmp.setCreatedDate(obj.getCreatedDate());
					tmp.setModifiedBy(obj.getModifiedBy());
					tmp.setModifiedDate(obj.getModifiedDate());
					tmp.setDescription(obj.getDescription());
					tmp.setEndTime(obj.getEndTime());
					tmp.setStartTime(obj.getStartTime());
					tmp.setStatus(obj.getStatus());
					lstShift.add(tmp);
				}
				s.setDateTime(dateTime);
				s.setShifts(lstShift);
				res.getSchedules().add(s);
			}
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HairStylistDTO> getLstAllHairStylistSchedule() {
		List<Map<String, Object>> queryData = hairStylistRepository.lstAllStylistSchedule();
		return commonFunctions.convertListQueryResultToDTO(queryData);
	}

	@Override
	public Object getStylishScheduleBookingByStylishId(Long id, String dateTime) {
		List<Map<String, Object>> queryData = hairStylistRepository.getStylishScheduleBookingByStylishId(id, dateTime);
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
	public Object getOneStylishScheduleBookingByStylishId(Long id) {
		List<Map<String, Object>> queryData = hairStylistRepository.getOneStylishScheduleBookingByStylishId(id);
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
	public void deleteHairStylist(Long id) {
		hairStylistRepository.deleteById(id);

	}

	@Override
	public Object getAllStylist() {
		List<Map<String, Object>> queryData = hairStylistRepository.getAllStylist();
		List<getStylistDTO> result = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper().registerModule(new ParameterNamesModule())
				.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
		for (Map<String, Object> res : queryData) {
			getStylistDTO obj = mapper.convertValue(res, getStylistDTO.class);
			result.add(obj);
		}
		return result;
	}

}
