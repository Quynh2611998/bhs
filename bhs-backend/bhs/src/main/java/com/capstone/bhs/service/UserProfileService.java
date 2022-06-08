package com.capstone.bhs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.bhs.model.dto.UserProfileDTO;
import com.capstone.bhs.model.entity.UserProfile;
import com.capstone.bhs.model.vm.UserProfileVM;

@Service
public interface UserProfileService {
	public List<UserProfile> getAllUserProfile();

	public void save(UserProfile userProfile);

	public void updateProfile(UserProfileVM userProfile);

	public List<UserProfileDTO> getAllListUserProfile();

}
