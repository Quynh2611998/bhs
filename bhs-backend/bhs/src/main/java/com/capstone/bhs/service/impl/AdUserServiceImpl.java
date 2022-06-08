package com.capstone.bhs.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstone.bhs.common.CommonConstant;
import com.capstone.bhs.model.entity.AdUser;
import com.capstone.bhs.model.entity.UserProfile;
import com.capstone.bhs.model.vm.AdUserVM;
import com.capstone.bhs.model.vm.RegisterVM;
import com.capstone.bhs.repository.AdUserRepository;
import com.capstone.bhs.repository.UserProfileRepository;
import com.capstone.bhs.service.AdUserService;

@Service
public class AdUserServiceImpl implements AdUserService {

	@Autowired
	private AdUserRepository adUserRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Override
	public AdUser registerUser(RegisterVM regUser) {
		adUserRepository.findOneByUsername(regUser.getEmail()).ifPresent(existingUser -> {
			boolean removed = this.removeNonActivatedUser(existingUser);
			if (!removed) {
				throw new RuntimeErrorException(null);
			}
		});
		AdUser saveObj = new AdUser();
		saveObj.setActive(false);
		saveObj.setUsername(regUser.getEmail());
		saveObj.setPassword(passwordEncoder.encode(regUser.getPassword()));
		saveObj.setActiveKey(UUID.randomUUID().toString());
		saveObj.setRole(CommonConstant.Authorities.ROLE_USER);
		adUserRepository.save(saveObj);
		return saveObj;
	}

	@Override
	public Optional<AdUser> activateAccount(String activeKey) {
		return adUserRepository.findOneByActiveKey(activeKey).map(user -> {
			user.setActive(true);
			user.setActiveKey(null);
			adUserRepository.save(user);
			return user;
		});
	}

	public Optional<AdUser> requestPasswordReset(String mail) {
		return adUserRepository.findOneByUsernameIgnoreCase(mail).filter(AdUser::isActive).map(user -> {
			user.setResetKey(UUID.randomUUID().toString());
			user.setModifiedDate(Instant.now());
			adUserRepository.save(user);
			return user;
		});
	}

	public Optional<AdUser> completePasswordReset(String newPassword, String key) {
		return adUserRepository.findOneByResetKey(key)
				.filter(user -> user.getModifiedDate().isAfter(Instant.now().minusSeconds(86400))).map(user -> {
					user.setPassword(passwordEncoder.encode(newPassword));
					user.setResetKey(null);
					adUserRepository.save(user);
					return user;
				});
	}

	private boolean removeNonActivatedUser(AdUser existingUser) {
		if (existingUser.isActive()) {
			return false;
		}
		adUserRepository.delete(existingUser);
		Optional<UserProfile> userProfile = userProfileRepository.findOneByUserId(existingUser.getId());
		if (userProfile.isPresent()) {
			userProfileRepository.delete(userProfile.get());
			userProfileRepository.flush();
		}
		adUserRepository.flush();
		return true;
	}

	@Override
	public Optional<AdUser> unlockAccount(AdUserVM unlock, Long id) {
		return adUserRepository.findOneById(id).map(user -> {
			boolean isLock = false;
			user.setLocked(isLock);
			adUserRepository.save(user);
			return user;
		});

	}

	@Override
	public Optional<AdUser> lockAccount(AdUserVM lock, Long id) {
		return adUserRepository.findOneById(id).map(user -> {
			boolean isLock = true;
			user.setLocked(isLock);
			adUserRepository.save(user);
			return user;
		});
	}

	@Override
	public List<AdUser> getAllListAccount() {
		// TODO Auto-generated method stub
		return adUserRepository.findAll();
	}

	@Override
	public Optional<AdUser> getUserById(Long id) {
		return adUserRepository.findById(id);
	}

}
