package com.capstone.bhs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.bhs.common.HeaderUtil;
import com.capstone.bhs.common.SpringSecurityUtils;
import com.capstone.bhs.model.dto.UserProfileDTO;
import com.capstone.bhs.model.entity.AdUser;
import com.capstone.bhs.model.entity.UserProfile;
import com.capstone.bhs.model.vm.UserProfileVM;
import com.capstone.bhs.repository.AdUserRepository;
import com.capstone.bhs.repository.UserProfileRepository;
import com.capstone.bhs.service.UserProfileService;

@RestController
@RequestMapping("/api/user-profile")
public class UserProfileController {

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Autowired
	private AdUserRepository adUserRepository;

	@GetMapping("/get-all-list-user-profile")
	public ResponseEntity<List<UserProfileDTO>> getAllListUserProfile() {
		List<UserProfileDTO> lstData = userProfileService.getAllListUserProfile();
		return ResponseEntity.ok().body(lstData);
	}

	@GetMapping("/get-user-profile")
	public ResponseEntity<UserProfile> getUserProfile() {
		String username = SpringSecurityUtils.getCurrentUserLogin().get();
		Optional<AdUser> account = adUserRepository.findOneByUsernameIgnoreCase(username);
		if (account.isPresent()) {
			Optional<UserProfile> userProfile = userProfileRepository.findOneByUserId(account.get().getId());
			if (userProfile.isPresent()) {
				return ResponseEntity.ok().body(userProfile.get());
			}
		}
		return ResponseEntity.ok().body(new UserProfile());
	}

	@PutMapping("/update-user-profile")
	public ResponseEntity<?> updateUserProfile(@RequestBody UserProfileVM userProfile) {
		userProfileService.updateProfile(userProfile);
		return ResponseEntity.ok().headers(HeaderUtil.createAlert("Profile Updated", "Profile Updated")).body(null);
	}

	@GetMapping("get-user-profile-by-id/{id}")
	public ResponseEntity<?> getUserProfileByID(@PathVariable("id") Long id) {
		Optional<UserProfile> obj = userProfileRepository.findOneByUserId(id);
		if (obj.isPresent()) {
			return ResponseEntity.ok().body(obj.get());
		} else {
			return ResponseEntity.badRequest().headers(
					HeaderUtil.createFailureAlert("UserProfile", "Profile doesn't exist!", "Profile doesn't exist!"))
					.body(new UserProfile());
		}
	}

}
