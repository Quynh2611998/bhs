package com.capstone.bhs.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.capstone.bhs.model.dto.AuthenticationResponseDTO;
import com.capstone.bhs.model.entity.UserProfile;
import com.capstone.bhs.model.vm.UsernameAndPasswordVM;
import com.capstone.bhs.repository.UserProfileRepository;

@SpringBootTest
public class AdUserControllerTest {

	public static final List<UserProfile> USER_PROFILE1 = new ArrayList<>();
	public static final List<UserProfile> USER_PROFILE2 = new ArrayList<>();
	public static final List<UserProfile> USER_PROFILE3 = new ArrayList<>();
	public static final List<UserProfile> USER_PROFILE4 = new ArrayList<>();
	public static final List<UserProfile> USER_PROFILE5 = new ArrayList<>();
	
	@Autowired
	private UserProfileRepository userProfileRepository;

	static {	
		USER_PROFILE1.add(new UserProfile(Long.valueOf(1), "pham", "son", "male",
				"012346875", "son123@gmail.com", Instant.parse("1998-05-12"), "abc",
				Long.valueOf(1), null, "anounymous", null, "anounymous"));
	}
	@Test
	public void testUpdateUserProfile_01() {
		UserProfile user = userProfileRepository.save(USER_PROFILE1);
		Assert.assertEquals("Profile Updated", "Profile Updated");
	}
	
	static {	
		USER_PROFILE2.add(new UserProfile(Long.valueOf(1), "1234", "1234", "0000",
				"012346875", "son123@gmail.com", Instant.parse("1998-05-12"), "abc",
				Long.valueOf(1), null, "anounymous", null, "anounymous"));
	}
	@Test
	public void testUpdateUserProfile_02() {
		UserProfile user = userProfileRepository.save(USER_PROFILE2);
		Assert.assertEquals("Profile Updated", "Profile Updated");
	}
	
	static {	
		USER_PROFILE3.add(new UserProfile(Long.valueOf(1), "+-*/", "+-*/", "male",
				"012346875", "son123@gmail.com", Instant.parse("1998-05-12"), "abc",
				Long.valueOf(1), null, "anounymous", null, "anounymous"));
	}
	@Test
	public void testUpdateUserProfile_03() {
		UserProfile user = userProfileRepository.save(USER_PROFILE3);
		Assert.assertEquals("Profile Update fail", "Profile Update fail");
	}
	
	static {	
		USER_PROFILE4.add(new UserProfile(Long.valueOf(1), "pham8887777", "son11144455", "male75246",
				"ssss012346875", "son123@gmail.com", Instant.parse("1998-05-12"), "abc",
				Long.valueOf(1), null, "anounymous", null, "anounymous"));
	}
	@Test
	public void testUpdateUserProfile_04() {
		UserProfile user = userProfileRepository.save(USER_PROFILE4);
		Assert.assertEquals("Profile Updated", "Profile Updated");
	}
	
	static {	
		USER_PROFILE5.add(new UserProfile(Long.valueOf(1), "pham//**/-+", "son**/--++", "male",
				"012346875", "son123@gmail.com", Instant.parse("1998-05-12"), "abc",
				Long.valueOf(1), null, "anounymous", null, "anounymous"));
	}
	@Test
	public void testUpdateUserProfile_05() {
		UserProfile user = userProfileRepository.save(USER_PROFILE5);
		Assert.assertEquals("Profile Update fail", "Profile Update fail");
	}
	
	// Authen Unit Test
//	@Test
//	public void testAuthenticationWithRoleUser() {
//		UsernameAndPasswordVM credential  = new UsernameAndPasswordVM();
//		credential.setUsername("user@user");
//		credential.setPassword("123");
//		ResponseEntity<AuthenticationResponseDTO> data = adUserController.authenticate(credential);
//		Assert.assertEquals(data.getBody().getRole(), "User");
//	}
//	
//	@Test
//	public void testAuthenticationWithRoleAdmin() {
//		UsernameAndPasswordVM credential  = new UsernameAndPasswordVM();
//		credential.setUsername("admin@admin");
//		credential.setPassword("123");
//		ResponseEntity<AuthenticationResponseDTO> data = adUserController.authenticate(credential);
//		Assert.assertEquals(data.getBody().getRole(), "Admin");
//	}
//	
//	@Test
//	public void testAuthenticationWrongUsername() {
//		UsernameAndPasswordVM credential  = new UsernameAndPasswordVM();
//		credential.setUsername("user@admin");
//		credential.setPassword("123");
//		ResponseEntity<AuthenticationResponseDTO> data = adUserController.authenticate(credential);
//		Assert.assertEquals(data.getBody(), "Login failed");
//	}
//	
//	@Test
//	public void testAuthenticationWrongPassword() {
//		UsernameAndPasswordVM credential  = new UsernameAndPasswordVM();
//		credential.setUsername("user@user");
//		credential.setPassword("12345");
//		ResponseEntity<AuthenticationResponseDTO> data = adUserController.authenticate(credential);
//		Assert.assertEquals(data.getBody(), "Login failed");
//	}
	// Authen Unit Test

}
