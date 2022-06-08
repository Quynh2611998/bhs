package com.capstone.bhs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.bhs.model.entity.Shift;
import com.capstone.bhs.service.ShiftService;

@RestController
@RequestMapping("/api")
public class ShiftController {

	@Autowired
	private ShiftService shiftService;

	@GetMapping("/get-all-list-shift")
	public ResponseEntity<List<Shift>> getAllListShift() {
		return ResponseEntity.ok().body(shiftService.getAllListShifts());
	}

}
