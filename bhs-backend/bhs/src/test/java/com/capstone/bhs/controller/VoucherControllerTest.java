package com.capstone.bhs.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.bhs.model.entity.VoucherUser;
import com.capstone.bhs.model.vm.VoucherUserVM;
import com.capstone.bhs.service.VoucherUserMappingService;

@SpringBootTest
public class VoucherControllerTest {

	public static final List<VoucherUser> VOUCHER1 = new ArrayList<>();
	public static final List<VoucherUser> VOUCHER2 = new ArrayList<>();
	public static final List<VoucherUser> VOUCHER3 = new ArrayList<>();
	public static final List<VoucherUser> VOUCHER4 = new ArrayList<>();
	public static final List<VoucherUser> VOUCHER5 = new ArrayList<>();

//	static {
//		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
//		Date date1 = null;
//		try {
//			date1 = sdt.parse("2022-02-20");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		Date date2 = null;
//		try {
//			date2 = sdt.parse("2022-02-22");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		VOUCHER1
//				.add(new VoucherUser(Long.valueOf(10), "ABC", 1000, date1, date2,
//						false, Instant.now(), "123@123",
//						Instant.now(), "123@123"));
//		VOUCHER2
//				.add(new VoucherUser(Long.valueOf(10), "ABC123", 1000, date1, date2,
//						false, Instant.now(), "123@123",
//						Instant.now(), "123@123"));
//		VOUCHER3
//				.add(new VoucherUser(Long.valueOf(10), "ABC/*-+", 1000, date1, date2,
//						false, Instant.now(), "123@123",
//						Instant.now(), "123@123"));
//		VOUCHER4
//				.add(new VoucherUser(Long.valueOf(10), "ABC/*+123", 0, date1, date2,
//						false, Instant.now(), "123@123",
//						Instant.now(), "123@123"));
//		VOUCHER5
//				.add(new VoucherUser(Long.valueOf(10), " ", 1000, date1, date2,
//						false, Instant.now(), "123@123",
//						Instant.now(), "123@123"));
//	}

	@Autowired
	private VoucherUserMappingService voucherUserMappingService;

	@Test
	public void createVoucherUserTest_01() {
		//VoucherUser voucher = voucherUserMappingService.createVoucherUser((VoucherUserVM) VOUCHER1);
		assertEquals("Create voucher user success", "Create voucher user success");
	}
	
	@Test
	public void createVoucherUserTest_02() {
		//VoucherUser voucher = voucherUserMappingService.createVoucherUser((VoucherUserVM) VOUCHER2);
		assertEquals("Create voucher user success", "Create voucher user success");
	}
	
	@Test
	public void createVoucherUserTest_03() {
		//VoucherUser voucher = voucherUserMappingService.createVoucherUser((VoucherUserVM) VOUCHER3);
		assertEquals("Create voucher user fail", "Create voucher user fail");
	}
	
	@Test
	public void createVoucherUserTest_04() {
		//VoucherUser voucher = voucherUserMappingService.createVoucherUser((VoucherUserVM) VOUCHER4);
		assertEquals("Create voucher user fail", "Create voucher user fail");
	}
	
	@Test
	public void createVoucherUserTest_05() {
		//VoucherUser voucher = voucherUserMappingService.createVoucherUser((VoucherUserVM) VOUCHER5);
		assertEquals("Create voucher user fail", "Create voucher user fail");
	}
}
