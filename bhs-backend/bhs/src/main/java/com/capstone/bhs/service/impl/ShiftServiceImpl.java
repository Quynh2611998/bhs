package com.capstone.bhs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.bhs.model.entity.Shift;
import com.capstone.bhs.repository.ShiftRepository;
import com.capstone.bhs.service.ShiftService;

@Service
public class ShiftServiceImpl implements ShiftService{

	@Autowired
	private ShiftRepository shiftRepository;
	@Override
	public List<Shift> getAllListShifts() {
		return shiftRepository.findAll();
	}

}
