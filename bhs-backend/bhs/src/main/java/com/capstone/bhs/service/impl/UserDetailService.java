package com.capstone.bhs.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capstone.bhs.model.dto.UserDetailsDTO;
import com.capstone.bhs.model.entity.AdUser;
import com.capstone.bhs.repository.AdUserRepository;

@Service
public class UserDetailService implements UserDetailsService{

	@Autowired
	private AdUserRepository adUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AdUser> user = adUserRepository.findOneByUsername(username);
		return user.map(UserDetailsDTO::new).get();
	}

}
