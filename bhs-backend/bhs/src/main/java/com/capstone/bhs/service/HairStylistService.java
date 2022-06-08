package com.capstone.bhs.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.capstone.bhs.model.dto.HairStylistDTO;
import com.capstone.bhs.model.entity.HairStylist;
import com.capstone.bhs.model.vm.HairStylistVM;

public interface HairStylistService {

	public List<HairStylist> getAllHairStylist();

	public HairStylist createHairStylist(HairStylistVM stylistVM);

	public Optional<HairStylist> updateHairStylist(HairStylistVM stylistVM, Long id);

	void delete(long[] ids);

	public Optional<HairStylist> getHairStylistById(Long id);

	public Object getStylishScheduleByStylishId(Long id);

	public List<HairStylistDTO> getLstAllHairStylistSchedule();

	public Object getStylishScheduleBookingByStylishId(Long id, String dateTime);

	public Object getOneStylishScheduleBookingByStylishId(Long id);

	public void deleteHairStylist(Long id);

	public Object getAllStylist();
}
