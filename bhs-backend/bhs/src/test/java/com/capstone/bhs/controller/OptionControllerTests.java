package com.capstone.bhs.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.bhs.model.entity.Options;
import com.capstone.bhs.service.OptionsService;

@SpringBootTest
class OptionControllerTests {
//	private static final List<Options> lst = new ArrayList<>();
//	private static final List<Options> lst1 = new ArrayList<>();
//	private static final List<Options> lst2 = new ArrayList<>();

//	static {
//		lst.add(new Options(Long.valueOf(1), "Toc Undercut 01", Long.valueOf(24), 80000,
//				Instant.parse("2022-03-20T11:08:19Z"), "anounymous", Instant.parse("2022-03-20T11:08:19Z"),
//				"anounymous"));
//		lst.add(new Options(Long.valueOf(2), "Toc Undercut 02", Long.valueOf(24), 85000,
//				Instant.parse("2022-03-20T11:07:47Z"), "anounymous", Instant.parse("2022-03-20T11:07:47Z"),
//				"anounymous"));
//	}
//
//	static {
//		lst2.add(new Options(Long.valueOf(1), "Toc Undercut 01", Long.valueOf(24), 80000,
//				Instant.parse("2022-03-20T11:08:19Z"), "anounymous", Instant.parse("2022-03-20T11:08:19Z"),
//				"anounymous"));
//		lst2.add(new Options(Long.valueOf(2), "Toc Undercut 02", Long.valueOf(24), 85000,
//				Instant.parse("2022-03-20T11:07:47Z"), "anounymous", Instant.parse("2022-03-20T11:07:47Z"),
//				"anounymous"));
//		lst2.add(new Options(Long.valueOf(2), "Toc Undercut 03", Long.valueOf(24), 85000,
//				Instant.parse("2022-03-20T11:07:26Z"), "anounymous", Instant.parse("2022-03-20T11:07:26"),
//				"anounymous"));
//		lst2.add(new Options(Long.valueOf(2), "Toc Undercut 04", Long.valueOf(24), 85000,
//				Instant.parse("2022-03-20T11:07:51Z"), "anounymous", Instant.parse("2022-03-20T11:07:51Z"),
//				"anounymous"));
//		lst2.add(new Options(Long.valueOf(2), "Toc Undercut 05", Long.valueOf(24), 85000,
//				Instant.parse("2022-03-20T11:07:49Z"), "anounymous", Instant.parse("2022-03-20T11:07:49Z"),
//				"anounymous"));
//	} 
//	
//	@Autowired
//	private OptionsService optionsService;
//
//	@Test
//	public void searchOptionByKeyword_01() {
//		List<Options> lstData = optionsService.searchOptionByKeyword("toc");
//		Assert.assertEquals(lst, lstData);
//	}
//	
//	@Test
//	public void searchOptionByKeyword_02() {
//		List<Options> lstData = optionsService.searchOptionByKeyword("toc");
//		Assert.assertEquals(lst.size(), lstData);
//	}
//
//	@Test
//	public void searchOptionByKeyword_03() {
//		List<Options> lstData = optionsService.searchOptionByKeyword("/");
//		Assert.assertEquals(lst1, lstData);
//	}
//	
//	@Test
//	public void searchOptionByKeyword_05() {
//		List<Options> lstData = optionsService.searchOptionByKeyword(" ");
//		Assert.assertEquals(lst2, lstData);
//	}
//	
//	@Test
//	public void searchOptionByKeyword_06() {
//		List<Options> lstData = optionsService.searchOptionByKeyword("-4");
//		Assert.assertEquals(lst1, lstData);
//	}

}
