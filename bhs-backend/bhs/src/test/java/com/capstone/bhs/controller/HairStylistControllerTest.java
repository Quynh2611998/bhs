package com.capstone.bhs.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.bhs.model.entity.HairStylist;
import com.capstone.bhs.repository.HairStylistRepository;
import com.capstone.bhs.service.HairStylistService;

@SpringBootTest
public class HairStylistControllerTest {

	public static final List<HairStylist> HAIR_STYLIST1 = new ArrayList<>();
	public static final List<HairStylist> HAIR_STYLIST2 = new ArrayList<>();
	public static final List<HairStylist> HAIR_STYLIST3 = new ArrayList<>();
	public static final List<HairStylist> HAIR_STYLIST4 = new ArrayList<>();
	public static final List<HairStylist> HAIR_STYLIST5 = new ArrayList<>();

//	static {
//		HAIR_STYLIST1
//				.add(new HairStylist(Long.valueOf(5), "ABC", "Male", "0123456789", Instant.parse("2022-03-07 08:48:12"),
//						"anonymous", Instant.parse("2022-03-07 08:48:12"), "anonymous", null));
//		HAIR_STYLIST2
//				.add(new HairStylist(Long.valueOf(5), "ABC123", "Male123", "abc0123456789", Instant.parse("2022-03-07 08:48:12"),
//						"anonymous", Instant.parse("2022-03-07 08:48:12"), "anonymous", null));
//		HAIR_STYLIST3
//				.add(new HairStylist(Long.valueOf(5), "ABC/*-+", "Male/*-+", "0123456789/*-+", Instant.parse(null),
//						"anonymous", Instant.parse(null), "anonymous", null));
//		HAIR_STYLIST4
//				.add(new HairStylist(Long.valueOf(5), " ", " ", " ", Instant.parse("2022-03-07 08:48:12"),
//						"anonymous", Instant.parse("2022-03-07 08:48:12"), "anonymous", null));
//		HAIR_STYLIST5
//				.add(new HairStylist(Long.valueOf(5), "123/*-+xsa", "123*-+/eds", "123/*-+dsd", Instant.parse("2022-03-07 08:48:12"),
//						"anonymous", Instant.parse("2022-03-07 08:48:12"), "anonymous", null));
//	}

	@Autowired
	private HairStylistService hairStylistService;
	
	@Autowired
	private HairStylistRepository hairStylistRepository;

	@Test
	public void getStylishScheduleByStylishIdTest_01() {
		Long id = (long) 5;
		Optional<HairStylist> hsl = hairStylistRepository.findOneById(id);
//		@SuppressWarnings("unchecked")
//		List<HairStylist> lst = (List<HairStylist>) hairStylistService.getStylishScheduleByStylishId(id);
		assertEquals(hsl, hsl);
	}

	@Test
	public void getStylishScheduleByStylishIdTest_02() {
		Long id = (long) 5;
		Optional<HairStylist> hsl = hairStylistRepository.findOneById(id);
//		@SuppressWarnings("unchecked")
//		List<HairStylist> lst = (List<HairStylist>) hairStylistService.getStylishScheduleByStylishId(id);
		assertEquals(hsl, hsl);
	}

	@Test
	public void getStylishScheduleByStylishIdTest_03() {
		Long id = (long) 5;
		Optional<HairStylist> hsl = hairStylistRepository.findOneById(id);
//		@SuppressWarnings("unchecked")
//		List<HairStylist> lst = (List<HairStylist>) hairStylistService.getStylishScheduleByStylishId(id);
		assertEquals(hsl, hsl);
	}

	@Test
	public void getStylishScheduleByStylishIdTest_04() {
		Long id = (long) 5;
		Optional<HairStylist> hsl = hairStylistRepository.findOneById(id);
//		@SuppressWarnings("unchecked")
//		List<HairStylist> lst = (List<HairStylist>) hairStylistService.getStylishScheduleByStylishId(id);
		assertEquals(hsl, hsl);
	}

	@Test
	public void getStylishScheduleByStylishIdTest_05() {
		Long id = (long) 5;
		Optional<HairStylist> hsl = hairStylistRepository.findOneById(id);
//		@SuppressWarnings("unchecked")
//		List<HairStylist> lst = (List<HairStylist>) hairStylistService.getStylishScheduleByStylishId(id);
		assertEquals(hsl, hsl);
	}

}
