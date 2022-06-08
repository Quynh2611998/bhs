package com.capstone.bhs.service;

import java.util.List;
import java.util.Optional;

import com.capstone.bhs.model.entity.AdUser;
import com.capstone.bhs.model.vm.AdUserVM;
import com.capstone.bhs.model.vm.RegisterVM;

public interface AdUserService {

	public List<AdUser> getAllListAccount();

	public AdUser registerUser(RegisterVM regUser);

	public Optional<AdUser> activateAccount(String activeKey);

	public Optional<AdUser> requestPasswordReset(String mail);

	public Optional<AdUser> completePasswordReset(String newPassword, String key);

	public Optional<AdUser> lockAccount(AdUserVM lock, Long id);

	public Optional<AdUser> unlockAccount(AdUserVM unlock, Long id);

	public Optional<AdUser> getUserById(Long id);

}
