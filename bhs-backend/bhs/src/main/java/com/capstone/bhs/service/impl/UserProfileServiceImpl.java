package com.capstone.bhs.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.bhs.common.CommonFunction;
import com.capstone.bhs.model.dto.UserProfileDTO;
import com.capstone.bhs.model.entity.UserProfile;
import com.capstone.bhs.model.vm.UserProfileVM;
import com.capstone.bhs.repository.UserProfileRepository;
import com.capstone.bhs.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	@SuppressWarnings("rawtypes")
	private CommonFunction commonFunction = new CommonFunction();

	@Autowired
	private UserProfileRepository repository;

	@Override
	public List<UserProfile> getAllUserProfile() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void save(UserProfile userProfile) {
		repository.save(userProfile);
	}

	@Override
	public void updateProfile(UserProfileVM userProfile) {
		Optional<UserProfile> optional = repository.findById(userProfile.getId());
		UserProfile profileToSave = null;
		if (optional.isPresent()) {
			profileToSave = optional.get();
		} else {
			profileToSave = new UserProfile();
		}
		profileToSave.setDateOfBirth(userProfile.getDateOfBirth());
		profileToSave.setFirstName(userProfile.getFirstName());
		profileToSave.setLastName(userProfile.getLastName());
		profileToSave.setGender(userProfile.getGender());
		profileToSave.setPhoneNumber(userProfile.getPhoneNumber());
		profileToSave.setProfileImage(userProfile.getProfileImage());
		repository.save(profileToSave);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserProfileDTO> getAllListUserProfile() {
		List<Map<String, Object>> queryData = repository.getAllListUserProfile();
		return commonFunction.convertListQueryResultToDTO(queryData);
	}

}
