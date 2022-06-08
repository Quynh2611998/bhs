package com.capstone.bhs.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capstone.bhs.model.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{

	Optional<UserProfile> findOneByUserId(Long id);

	UserProfile save(List<UserProfile> userProfile);
	
	@Query(value = "SELECT " +
			"up.`id` AS id, " + 
			"au.`username` AS userName, " + 
			"up.`first_name` AS firstName, " + 
			"up.`last_name` AS lastName, " + 
			"au.`role` AS role, " + 
			"up.`gender` AS gender, " + 
			"up.`date_of_birth` AS DoB, " + 
			"up.`email` AS email, " + 
			"up.`phone_number` AS phoneNumber, " + 
			"au.`is_locked` AS isLocked, " + 
			"up.`profile_image` AS imageProfile, " + 
			"up.`created_date` AS createdDate, " + 
			"up.`created_by` AS createdBy, " + 
			"up.`modified_date` AS modifiedDate, " + 
			"up.`modified_by` AS modifiedBy " + 
			"FROM `user_profile` up " + 
			"LEFT JOIN `ad_user` au " + 
			"ON au.`id` = up.`user_id`", nativeQuery = true)
	List<Map<String, Object>> getAllListUserProfile();

}
