package com.capstone.bhs.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.capstone.bhs.model.entity.HairStylist;

@Repository
public interface HairStylistRepository extends JpaRepository<HairStylist, Long> {
	Optional<HairStylist> findOneById(Long Id);

	Optional<HairStylist> findOneBystylistName(String stylistName);

	Optional<HairStylist> findOneById(HairStylist stylistId);

	@Query(value = " SELECT " 
			+ " d.`id` AS id, " 
			+ " d.`stylist_name` AS stylistName, "
			+ " a.`date_time` AS `dateTime`, " 
			+ " b.`start_time` AS startTime, " 
			+ " b.`end_time` AS endTime, "
			+ " c.booking_note AS bookingNote, " 	
			+ " c.`description`AS description, "
			+ " c.`status` AS status, "
			+ " c.`created_date` AS createdDate, " 
			+ " c.`created_by` AS createdBy, "
			+ " c.`modified_date` AS modifiedDate, "
			+ " c.`modified_by` AS modifiedBy " 
			+ " FROM `stylist_schedule` a "
			+ " LEFT JOIN `shift` b ON a.`shift_id` = b.id "
			+ " LEFT JOIN `schedule_booking` c ON c.`schedule_id` = a.`id` "
			+ " LEFT JOIN  `hair_stylist` d ON d.`id` = a.`stylist_id` " 
			+ "WHERE a.date_time = CURDATE() AND d.`id` = ? "
			+ " ORDER BY a.`date_time`, b.`start_time` ", nativeQuery = true)
	List<Map<String, Object>> getStylishScheduleByStylishId(@Param("stylistId") Long stylistId);
	
	@Query(value = "SELECT   							  " + 
			"						 " + 
			"			a.`id` AS id,    " + 
			"			d.`stylist_name` AS stylistName,  " + 
			"			a.`date_time` AS dateTime, " + 
			"			a.`start_time` AS startTime,   			  " + 
			"			a.`end_time` AS endTime, 	 " +
			"			a.`total_duration` AS totalDuration, " + 
			"			a.`note` AS note,   		  					  					  				  " + 
			"			a.`status` AS status,   					   " + 
			"			a.`created_date` AS createdDate,  		 " + 
			"			a.`created_by` AS createdBy,  			  " + 
			"			a.`modified_date` AS modifiedDate,  		 " + 
			"			a.`modified_by` AS modifiedBy  			 " + 
			"			FROM booking 				  a   											  " + 
			"			LEFT JOIN user_profile 		          b  ON a.`user_profile_id` = b.`id`   		  " + 
			"			LEFT JOIN hair_stylist 		          d  ON a.`hair_stylist_id` = d.`id`  			  		 			  	 							 				  " + 
			"			WHERE a.`hair_stylist_id` = ? AND a.`date_time` =  ? AND a.`status`= 'booked' " + 
			"			ORDER BY a.`date_time`, a.`start_time` ", nativeQuery = true)
	List<Map<String, Object>> getStylishScheduleBookingByStylishId(@Param("stylistId") Long stylistId, @Param("dateTime") String dateTime);
	
	@Query(value = "SELECT   							    				 " + 
			"						a.`id` AS id,       					 " + 
			"						d.`stylist_name` AS stylistName, 		 " + 
			"						b.`id` AS userProfileId, 				 " + 
			"						b.`first_name` AS firstName, 			 " + 
			"						b.`last_name` AS lastName,   			 " + 
			"						b.`phone_number` AS phoneNumber,   	     " + 
			"						a.`date_time` AS dateTime,   			 " + 
			"						a.`start_time` AS startTime,   			 " + 
			"						a.`end_time` AS endTime, 	   			 " + 
			"						a.`total_duration` AS totalDuration,     " + 
			"						a.`note` AS note,   		  			 " + 
			"						a.`status` AS status,   				 " + 
			"						a.`created_date` AS createdDate,  		 " + 
			"						a.`created_by` AS createdBy,  			 " + 
			"						a.`modified_date` AS modifiedDate,  	 " + 
			"						a.`modified_by` AS modifiedBy  			 " + 
			"						FROM booking 				          a  " + 
			"						LEFT JOIN user_profile 		          b  ON a.`user_profile_id` = b.`id`   	  " + 
			"						LEFT JOIN hair_stylist 		          d  ON a.`hair_stylist_id` = d.`id`  	  " + 
			"						WHERE a.`hair_stylist_id` = ? AND a.`date_time` >= CURRENT_DATE() - INTERVAL 7 DAY " + 
			"						ORDER BY a.`date_time`, a.`start_time` ", nativeQuery = true)
	List<Map<String, Object>> getOneStylishScheduleBookingByStylishId(@Param("stylistId") Long stylistId);
	
	@Query(value = "SELECT  " + 
			"			 a.`id` AS id,   " + 
			"			 d.`stylist_name` AS stylistName,  " + 
			"			 a.`date_time` AS `dateTime`,  " + 
			"			 b.`start_time` AS startTime,  " + 
			"			 b.`end_time` AS endTime,  " + 
			"			 c.`note` AS bookingNote,  	 " + 
			"			 c.`phone_number` AS phoneNumber,  " + 
			"			 c.`status` AS STATUS,  " + 
			"			 c.`created_date` AS createdDate,   " + 
			"			 c.`created_by` AS createdBy,  " + 
			"			 c.`modified_date` AS modifiedDate,  " + 
			"			 c.`modified_by` AS modifiedBy  " + 
			"			 FROM `stylist_schedule` a  " + 
			"			 LEFT JOIN `shift` b ON a.`shift_id` = b.id  " + 
			"			 LEFT JOIN `booking` c ON c.`hair_stylist_id` = a.`stylist_id` " + 
			"			 LEFT JOIN  `hair_stylist` d ON d.`id` = a.`stylist_id`   " + 
			"			 ORDER BY a.`date_time`, b.`start_time` ", nativeQuery = true)
	List<Map<String, Object>> lstAllStylistSchedule();
	
	@Query(value =  "SELECT " +
			"b.`id` AS bookingId, " + 
			"h.`id` AS hairStylistId, " + 
			"h.`stylist_name` AS stylistName, " + 
			"a.`username` AS username, " +
			"u.`first_name` AS firstName, " + 
			"u.`last_name` AS lastName, " + 
			"b.`phone_number` AS phoneNumber, " + 
			"b.`date_time` AS `dateTime`, " + 
			"b.`start_time` AS startTime, " + 
			"b.`end_time` AS endTime, " + 
			"b.`total_duration` AS totalDuration, " + 
			"b.`note` AS note, " + 
			"b.`status` AS `status` " + 
			"FROM `booking` b " + 
			"LEFT JOIN `hair_stylist` h " + 
			"ON h.`id` = b.`hair_stylist_id` " + 
			"LEFT JOIN `user_profile` u " + 
			"ON u.`id` = b.`user_profile_id` " + 
			"LEFT JOIN `ad_user` a " + 
			"ON a.`id` = u.`user_id` " + 
			"ORDER BY b.`date_time`, b.`start_time`", nativeQuery = true)
	List<Map<String, Object>> getAllStylist();
}
