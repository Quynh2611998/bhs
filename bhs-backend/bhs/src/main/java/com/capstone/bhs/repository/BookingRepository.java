package com.capstone.bhs.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstone.bhs.model.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{

	Optional<Booking> findOneById(Long id);
	
	Optional<Booking> findOneByStartTime(String startTime);
	
	Optional<Booking> findOneByEndTime(String endTime);
	
	Optional<Booking> findOneByUserProfileId(Long id);
	
	Optional<Booking> findOneByDateTime(String dateTime);
	
	@Query(value = "SELECT  " 							+ 
			"a.`id` AS id,  "							+
			"b.`id` AS userProfileId, " 				+ 
			"ad.`username` AS userName,  " 				+ 
			"b.`first_name` AS firstName,  "    		+ 
			"b.`last_name` AS lastName,  "      		+ 
			"b.`gender` AS gender,  " 					+ 
			"b.`email` AS email,  " 					+ 
			"c.`option_name` optionName,  " 			+ 
			"e.`name` AS voucherOptionName,  "  		+ 
			"f.`name` AS voucherUserName,  " 			+ 
			"c.`price` AS price,  " 					+ 
			"c.`duration` duration,  " 					+ 
			"a.`price` AS totalPrice,  "				+ 
			"a.`total_duration` AS totalDuration,  "    + 
			"a.`phone_number` AS phoneNumber, "			+
			"a.`note` AS note,  " 						+ 
			"a.`status` AS status,  " 					+ 
			"a.`date_time` AS dateTime,  "				+
			"d.`id` AS hairStylistId, " 				+ 
			"d.`stylist_name` AS stylistName,  " 		+ 
			"MAX(h.`image_src`) AS imageSrc,  "			+ 
			"a.`start_time` AS startTime,  "			+ 
			"a.`end_time` AS endTime,   " 				+ 
			"a.`created_date` AS createdDate, " 		+
			"a.`created_by` AS createdBy, " 			+ 
			"a.`modified_date` AS modifiedDate, " 		+
			"a.`modified_by` AS modifiedBy " 			+
			"FROM booking 							  a  " 											+ 
			"LEFT JOIN user_profile 		          b  ON a.`user_profile_id` = b.`id`  " 		+ 
			"LEFT JOIN hair_stylist 		          d  ON a.`hair_stylist_id` = d.`id`  "			+ 
			"LEFT JOIN voucher_options 	              e  ON a.`voucher_option_id` = e.`id`  " 		+ 
			"LEFT JOIN voucher_user 		          f  ON a.`voucher_user_id` = f.`id`  " 		+ 
			"LEFT JOIN `booking_option_mapping` 	  gg ON a.`id` = gg.`booking_id`  "			  	+ 
			"LEFT JOIN `options` 		 	          c  ON gg.`option_id` = c.`id`  "				+ 
			"LEFT JOIN `option_image`		          h  ON c.`id` = h.`option_id`  "				+
			"LEFT JOIN `ad_user` ad ON ad.`id` = b.`user_id` " 										+ 
			"WHERE a.`id` = ? "		 +
			"GROUP BY  "			 + 
			"a.`id`, "				 +
			"b.`id`, "				 +
			"ad.`username`, " 	 	 + 
			"b.`first_name`, "       + 
			"b.`last_name`, "		 + 
			"b.`gender`, "			 + 
			"b.`email`, " 			 + 
			"c.`option_name`, " 	 + 
			"e.`name` , " 			 + 
			"f.`name` , "			 + 
			"c.`price`, "			 + 
			"c.`duration`, "		 + 
			"a.`price`, "			 + 
			"a.`total_duration`, "   + 
			"a.`phone_number`, "	 +
			"a.`note`, " 			 + 
			"a.`status`, "			 +
			"d.`id`, "			 	 + 
			"d.`stylist_name`, "	 +
			"a.`start_time`, "		 +
			"a.`end_time`, "		 +
			"a.`created_date`, "	 +
			"a.`created_by`, "		 + 
			"a.`modified_date`, "	 +
			"a.`modified_by` " , nativeQuery = true)
	List<Map<String, Object>> getBookingById(@Param("bookingId") Long bookingId);
	
//	@Query(value = "SELECT " 																+	 
//			"a.`id` AS id, " 																+ 
//			"b.`first_name` AS firstName, " 												+
//			"b.`last_name` AS lastName, "  													+ 
//			"a.`date_time` AS dateTime, " 													+ 
//			"d.`stylist_name` AS stylistName, " 											+ 
//			"a.`created_date`AS createdDate, " 												+ 
//			"a.`modified_date` AS modifiedDate " 											+ 
//			"FROM `booking` 						 a " 								    + 
//			"LEFT JOIN user_profile 		         b  ON a.`user_profile_id` = b.`id` "   + 
//			"LEFT JOIN hair_stylist 		         d  ON a.`hair_stylist_id` = d.`id` ", nativeQuery = true)
	@Query(value = "SELECT  " 							+ 
			"a.`id` AS id,  " 							+ 
			"b.`first_name` AS firstName,  "    		+ 
			"b.`last_name` AS lastName,  "      		+ 
			"b.`gender` AS gender,  " 					+ 
			"b.`email` AS email,  " 					+ 
			"c.`option_name` optionName,  " 			+ 
			"e.`name` AS voucherOptionName,  "  		+ 
			"f.`name` AS voucherUserName,  " 			+ 
			"c.`price` AS price,  " 					+ 
			"c.`duration` duration,  " 					+ 
			"a.`price` AS totalPrice,  "				+ 
			"a.`total_duration` AS totalDuration,  "    + 
			"a.`phone_number` AS phoneNumber, "			+
			"a.`note` AS note,  " 						+ 
			"a.`status` AS status,  " 					+ 
			"a.`date_time` AS dateTime,  " 				+ 
			"d.`stylist_name` AS stylistName,  " 		+ 
			"MAX(h.`image_src`) AS imageSrc,  "			+ 
			"a.`start_time` AS startTime,  " 			+ 
			"a.`end_time` AS endTime,  " 				+ 
			"a.`created_date` AS createdDate, " 		+
			"a.`created_by` AS createdBy, " 			+ 
			"a.`modified_date` AS modifiedDate, " 		+
			"a.`modified_by` AS modifiedBy " 			+
			"FROM booking 							  a  " 											+ 
			"LEFT JOIN user_profile 		          b  ON a.`user_profile_id` = b.`id`  " 		+ 
			"LEFT JOIN hair_stylist 		          d  ON a.`hair_stylist_id` = d.`id`  "			+ 
			"LEFT JOIN voucher_options 	              e  ON a.`voucher_option_id` = e.`id`  " 		+ 
			"LEFT JOIN voucher_user 		          f  ON a.`voucher_user_id` = f.`id`  " 		+ 
			"LEFT JOIN `booking_option_mapping` 	  gg ON a.`id` = gg.`booking_id`  "			  	+ 
			"LEFT JOIN `options` 		 	          c  ON gg.`option_id` = c.`id`  "				+ 
			"LEFT JOIN `option_image`		          h  ON c.`id` = h.`option_id`  " 				+ 
			"WHERE a.`date_time` = CURDATE() "			 											+
			"GROUP BY  "			 + 
			"a.`id`, " 				 + 
			"b.`first_name`, "       + 
			"b.`last_name`, "		 + 
			"b.`gender`, "			 + 
			"b.`email`, " 			 + 
			"c.`option_name`, " 	 + 
			"e.`name` , " 			 + 
			"f.`name` , "			 + 
			"c.`price`, "			 + 
			"c.`duration`, "		 + 
			"a.`price`, "			 + 
			"a.`total_duration`, "   + 
			"a.`phone_number`, "	 +
			"a.`note`, " 			 + 
			"a.`status`, "			 + 
			"d.`stylist_name`, "	 +
			"a.`start_time`, "		 +
			"a.`end_time`, "		 +
			"a.`created_date`, "	 +
			"a.`created_by`, "		 + 
			"a.`modified_date`, "	 +
			"a.`modified_by` " , nativeQuery = true)
	List<Map<String, Object>> getAllListBooking();
	
	
	@Query(value = "SELECT  " 							+ 
			"a.`id` AS id,  "							+
			"b.`id` AS userProfileId, " 				+ 
			"ad.`username` AS userName,  " 				+ 
			"b.`first_name` AS firstName,  "    		+ 
			"b.`last_name` AS lastName,  "      		+ 
			"b.`gender` AS gender,  " 					+ 
			"b.`email` AS email,  " 					+ 
			"e.`name` AS voucherOptionName,  "  		+ 
			"f.`name` AS voucherUserName,  " 			+ 	
			"a.`price` AS totalPrice,  "				+ 
			"a.`total_duration` AS totalDuration,  "    + 
			"a.`phone_number` AS phoneNumber, "			+
			"a.`note` AS note,  " 						+ 
			"a.`status` AS status,  " 					+ 
			"a.`date_time` AS dateTime,  "				+
			"d.`id` AS hairStylistId, " 				+ 
			"d.`stylist_name` AS stylistName,  " 		+ 
			"a.`start_time` AS startTime,  "			+ 
			"a.`end_time` AS endTime,   " 				+ 
			"a.`created_date` AS createdDate, " 		+
			"a.`created_by` AS createdBy, " 			+ 
			"a.`modified_date` AS modifiedDate, " 		+
			"a.`modified_by` AS modifiedBy " 			+
			"FROM booking 							  a  " 											+ 
			"LEFT JOIN user_profile 		          b  ON a.`user_profile_id` = b.`id`  " 		+ 
			"LEFT JOIN hair_stylist 		          d  ON a.`hair_stylist_id` = d.`id`  "			+ 
			"LEFT JOIN voucher_options 	              e  ON a.`voucher_option_id` = e.`id`  " 		+ 
			"LEFT JOIN voucher_user 		          f  ON a.`voucher_user_id` = f.`id`  " 		+ 
			"LEFT JOIN `booking_option_mapping` 	  gg ON a.`id` = gg.`booking_id`  "			  	+ 	
			"LEFT JOIN `ad_user` ad ON ad.`id` = b.`user_id` " 										+ 
			"WHERE b.`user_id` = ? AND a.`status`= 'booked'  "	 +
			"GROUP BY  "			 + 
			"a.`id`, "				 +
			"b.`id`, "				 +
			"ad.`username`, " 	 	 + 
			"b.`first_name`, "       + 
			"b.`last_name`, "		 + 
			"b.`gender`, "			 + 
			"b.`email`, " 			 + 
			"e.`name` , " 			 + 
			"f.`name` , "			 + 
			"a.`price`, "			 + 
			"a.`total_duration`, "   + 
			"a.`phone_number`, "	 +
			"a.`note`, " 			 + 
			"a.`status`, "			 +
			"d.`id`, "			 	 + 
			"d.`stylist_name`, "	 +
			"a.`start_time`, "		 +
			"a.`end_time`, "		 +
			"a.`created_date`, "	 +
			"a.`created_by`, "		 + 
			"a.`modified_date`, "	 +
			"a.`modified_by` " , nativeQuery = true)
	List<Map<String, Object>> getBookingByUserId(@Param("userId")Long userId);
	
	
	@Query(value = "SELECT  " 							+ 
			"a.`id` AS id,  " 							+ 
			"b.`first_name` AS firstName,  "    		+ 
			"b.`last_name` AS lastName,  "      		+ 
			"b.`gender` AS gender,  " 					+ 
			"b.`email` AS email,  " 					+ 
			"c.`option_name` optionName,  " 			+ 
			"e.`name` AS voucherOptionName,  "  		+ 
			"f.`name` AS voucherUserName,  " 			+ 
			"c.`price` AS price,  " 					+ 
			"c.`duration` duration,  " 					+ 
			"a.`price` AS totalPrice,  "				+ 
			"a.`total_duration` AS totalDuration,  "    + 
			"a.`phone_number` AS phoneNumber, "			+
			"a.`note` AS note,  " 						+ 
			"a.`status` AS status,  " 					+ 
			"a.`date_time` AS dateTime,  " 				+ 
			"d.`stylist_name` AS stylistName,  " 		+ 
			"MAX(h.`image_src`) AS imageSrc,  "			+ 
			"a.`start_time` AS startTime,  " 			+ 
			"a.`end_time` AS endTime,  " 				+ 
			"a.`created_date` AS createdDate, " 		+
			"a.`created_by` AS createdBy, " 			+ 
			"a.`modified_date` AS modifiedDate, " 		+
			"a.`modified_by` AS modifiedBy " 			+
			"FROM booking 							  a  " 											+ 
			"LEFT JOIN user_profile 		          b  ON a.`user_profile_id` = b.`id`  " 		+ 
			"LEFT JOIN hair_stylist 		          d  ON a.`hair_stylist_id` = d.`id`  "			+ 
			"LEFT JOIN voucher_options 	              e  ON a.`voucher_option_id` = e.`id`  " 		+ 
			"LEFT JOIN voucher_user 		          f  ON a.`voucher_user_id` = f.`id`  " 		+ 
			"LEFT JOIN `booking_option_mapping` 	  gg ON a.`id` = gg.`booking_id`  "			  	+ 
			"LEFT JOIN `options` 		 	          c  ON gg.`option_id` = c.`id`  "				+ 
			"LEFT JOIN `option_image`		          h  ON c.`id` = h.`option_id`  " 				+ 
			"GROUP BY  "			 + 
			"a.`id`, " 				 + 
			"b.`first_name`, "       + 
			"b.`last_name`, "		 + 
			"b.`gender`, "			 + 
			"b.`email`, " 			 + 
			"c.`option_name`, " 	 + 
			"e.`name` , " 			 + 
			"f.`name` , "			 + 
			"c.`price`, "			 + 
			"c.`duration`, "		 + 
			"a.`price`, "			 + 
			"a.`total_duration`, "   + 
			"a.`phone_number`, "	 +
			"a.`note`, " 			 + 
			"a.`status`, "			 + 
			"d.`stylist_name`, "	 +
			"a.`date_time`,	"		 +
			"a.`start_time`, "		 +
			"a.`end_time`, "		 +
			"a.`created_date`, "	 +
			"a.`created_by`, "		 + 
			"a.`modified_date`, "	 +
			"a.`modified_by` " 		 +
			"ORDER BY a.`date_time`, a.`start_time` " , nativeQuery = true)
	List<Map<String, Object>> getAllListBookingAdmin();
	
	@Query(value="SELECT " 						+ 
			"a.`id`, " 							+ 
			"b.`first_name`, " 					+ 
			"b.`last_name`, " 					+ 
			"b.`gender`, " 						+ 
			"b.`email`, " 						+ 
			"e.`name` AS Voucher_Option, "		+ 
			"f.`name` AS Voucher_User, " 		+ 
			"a.`price` AS total_price, " 		+ 
			"a.`total_duration`, " 				+ 
			"a.`phone_number`, "				+ 
			"a.`note`, " 						+ 
			"a.`status`, " 						+ 
			"a.`date_time`, " 					+ 
			"d.`stylist_name`, " 				+ 
			"a.`start_time`, " 					+ 
			"a.`end_time`, " 					+ 
			"a.`created_date`, " 				+ 
			"a.`created_by`, " 					+ 
			"a.`modified_date`, " 				+ 
			"a.`modified_by` " 					+ 
			"FROM booking 			        a " + 
			"LEFT JOIN user_profile 		         b  ON a.`user_profile_id` = b.`id` " + 
			"LEFT JOIN hair_stylist 		         d  ON a.`hair_stylist_id` = d.`id` " + 
			"LEFT JOIN voucher_options 	         e  ON a.`voucher_option_id` = e.`id` "   + 
			"LEFT JOIN voucher_user 		         f  ON a.`voucher_user_id` = f.`id` " + 
			"LEFT JOIN `booking_option_mapping`       gg ON a.`id` = gg.`booking_id` " 	  + 
			"GROUP BY  " 			+ 
			"a.`id`, " 				+ 
			"b.`first_name`, "		+ 
			"b.`last_name`, " 		+ 
			"b.`gender`, " 			+ 
			"b.`email`, " 			+ 
			"e.`name` , " 			+ 
			"f.`name` , " 			+ 
			"a.`phone_number`, " 	+ 
			"a.`price`, " 			+ 
			"a.`total_duration`, "  + 
			"a.`note`, " 			+ 
			"a.`status`, " 			+ 
			"a.`date_time`, " 		+ 
			"d.`stylist_name`, " 	+ 
			"a.`created_date`, "	+ 
			"a.`created_by`, " 		+ 
			"a.`modified_date`, " 	+ 
			"a.`modified_by` " 		+
			"ORDER BY a.`date_time`, a.`start_time` ", nativeQuery = true)
	List<Map<String, Object>> getAllListViewBooking();
	
	@Query(value="SELECT " + 
			"bom.`id` AS id, " + 
			"up.`first_name` AS firstName, " + 
			"up.`last_name` AS lastName, " +
			"o.`id` AS optionId, " + 
			"o.`option_name` AS optionName " + 
			"FROM `booking_option_mapping` bom " + 
			"LEFT JOIN `booking` b ON b.`id` = bom.`booking_id` " + 
			"LEFT JOIN `options` o ON o.`id` = bom.`option_id` " + 
			"LEFT JOIN `user_profile` up ON up.`id` = b.`user_profile_id` " + 
			"WHERE bom.`booking_id` = ? ", nativeQuery = true)
	List<Map<String, Object>> getBookingOptionByBookingId(@Param("bookingId") Long bookingId);
	
	@Query(value = "SELECT a.id AS id, b.id AS userProfileId, ad.username AS userName, b.firstName AS firstName, b.lastName AS lastName, b.gender AS gender, b.email AS email, e.name AS voucherOptionName, f.name AS voucherUserName, a.price AS totalPrice, a.totalDuration AS totalDuration, a.phoneNumber AS phoneNumber, a.note AS note, a.status AS status, a.dateTime AS dateTime, d.id AS hairStylistId, d.stylistName AS stylistName, a.startTime AS startTime, a.endTime AS endTime, a.createdDate AS createdDate, a.createdBy AS createdBy, a.modifiedDate AS modifiedDate, a.modifiedBy AS modifiedBy FROM Booking a LEFT JOIN UserProfile b ON a.userProfileId = b.id " 
			+ "LEFT JOIN HairStylist d ON a.hairStylistId = d.id " + 
			"LEFT JOIN VoucherOptions e ON a.voucherOptionId = e.id " + 
			"LEFT JOIN VoucherUser f ON a.voucherUserId = f.id " + 
			"LEFT JOIN AdUser ad ON ad.id = b.userId " + 
			"WHERE a.id = :id ")
	List<Map<String, Object>> getBookingByBookingId(@Param("id") Long bookingId);
	
	@Query(value = "SELECT b.`date_time` AS `dateTime`, b.`start_time` AS startTime " + 
			"FROM `booking` b " + 
			"WHERE b.`date_time` = ? " + 
			"AND b.`start_time` = ? " + 
			"AND b.`status` = 'booked'", nativeQuery = true)
	List<Map<String, Object>> findOneByDateTimeAndStartTime(@Param("date") String dateTime, @Param("startTime") String startTime);
	
	@Query(value = "SELECT " 						+ 
			
			"b.userId AS userId, " 					+ 
			"a.id AS bookingId, "					+
			"ad.username AS userName, " 			+ 
			"a.phoneNumber AS phoneNumber, "			+
			"d.stylistName AS stylistName, "		+
			"f.name AS voucherUserName, " 			+ 	
			"a.price AS totalPrice, "				+ 
			"a.totalDuration AS totalDuration, "    + 
			"a.status AS status, "					+
			"o.id AS optionId, " +
			"o.optionName AS optionName, " 			+ 
			"o.duration AS duration, " 				+ 
			"o.price AS price," 					+
			"a.dateTime AS dateTime, "				+
			"a.startTime AS startTime, "			+ 
			"a.endTime AS endTime, " 				+ 
			"a.createdDate AS createdDate, " 		+
			"a.createdBy AS createdBy, " 			+ 
			"a.modifiedDate AS modifiedDate, " 		+
			"a.modifiedBy AS modifiedBy " 			+
			"FROM Booking a "										+ 
			"LEFT JOIN UserProfile b ON a.userProfileId = b.id " 		+ 
			"LEFT JOIN HairStylist d ON a.hairStylistId = d.id "		+ 
			"LEFT JOIN VoucherUser f ON a.voucherUserId = f.id " 		+ 
			"LEFT JOIN BookingOptionMapping gg ON a.id = gg.bookingId " + 
			"LEFT JOIN Options o ON o.id = gg.optionId "				+
			"LEFT JOIN AdUser ad ON ad.id = b.userId " 					+ 
			"WHERE b.userId = :userId " +
			"ORDER BY a.id ")
	List<Map<String, Object>> getUserBookingByUserId(@Param("userId")Long userId);
	
	@Query(value ="SELECT b.`status` AS status, bo.`option_id` AS optionId, b.`id` AS bookingId, " +
			"s.`id` AS serviceId, c.`id` AS categoryId " +
			"FROM `booking` b " +
			"LEFT JOIN `booking_option_mapping` bo ON bo.`booking_id` = b.`id` " +
			"LEFT JOIN `options` o ON o.`id` = bo.`option_id` " +
			"LEFT JOIN `services` s ON s.`id` = o.`service_id` " +
			"LEFT JOIN `categories` c ON c.`id` = s.`categories_id` " +
			"WHERE b.`status` = 'booked' AND o.`id` = ?", nativeQuery = true)
	List<Map<String, Object>> getOptionInBooking(@Param("id") Long id);
	
	@Query(value ="SELECT b.`status` AS status, bo.`option_id` AS optionId, b.`id` AS bookingId, " +
			"s.`id` AS serviceId, c.`id` AS categoryId " +
			"FROM `booking` b " +
			"LEFT JOIN `booking_option_mapping` bo ON bo.`booking_id` = b.`id` " +
			"LEFT JOIN `options` o ON o.`id` = bo.`option_id` " +
			"LEFT JOIN `services` s ON s.`id` = o.`service_id` " +
			"LEFT JOIN `categories` c ON c.`id` = s.`categories_id` " +
			"WHERE b.`status` = 'booked' AND s.`id` = ?", nativeQuery = true)
	List<Map<String, Object>> getServiceInBooking(@Param("id") Long id);
	
	@Query(value ="SELECT b.`status` AS status, bo.`option_id` AS optionId, b.`id` AS bookingId, " +
			"s.`id` AS serviceId, c.`id` AS categoryId " +
			"FROM `booking` b " +
			"LEFT JOIN `booking_option_mapping` bo ON bo.`booking_id` = b.`id` " +
			"LEFT JOIN `options` o ON o.`id` = bo.`option_id` " +
			"LEFT JOIN `services` s ON s.`id` = o.`service_id` " +
			"LEFT JOIN `categories` c ON c.`id` = s.`categories_id` " +
			"WHERE b.`status` = 'booked' AND c.`id` = ?", nativeQuery = true)
	List<Map<String, Object>> getCategoryInBooking(@Param("id") Long id);
	
	@Query(value ="SELECT b.`status` AS status, bo.`option_id` AS optionId, b.`id` AS bookingId, " +
			"s.`id` AS serviceId, c.`id` AS categoryId " +
			"FROM `booking` b " +
			"LEFT JOIN `booking_option_mapping` bo ON bo.`booking_id` = b.`id` " +
			"LEFT JOIN `options` o ON o.`id` = bo.`option_id` " +
			"LEFT JOIN `services` s ON s.`id` = o.`service_id` " +
			"LEFT JOIN `categories` c ON c.`id` = s.`categories_id` " +
			"LEFT JOIN `hair_stylist` h ON h.`id` = b.`hair_stylist_id` " +
			"WHERE b.`status` = 'booked' AND h.`id` = ?", nativeQuery = true)
	List<Map<String, Object>> getHairStylistInBooking(@Param("id") Long id);
	
	@Query(value = "SELECT  " + 
			"b.`id` AS bookingId, " + 
			"a.`username` AS username, " + 
			"b.`date_time` AS `dateTime`, " + 
			"b.`start_time` AS startTime, " + 
			"b.`end_time` AS endTime, " + 
			"b.`status` AS `status` " + 
			"FROM `booking` b " + 
			"LEFT JOIN `user_profile` u " + 
			"ON u.`id` = b.`user_profile_id` " + 
			"LEFT JOIN `ad_user` a " + 
			"ON a.`id` = u.`user_id` " + 
			"WHERE b.`status` = 'cancelled by user' " + 
			"AND u.`user_id` = ?  " + 
			"AND b.`date_time` = ? " + 
			"AND b.`start_time` = ?", nativeQuery = true)
	List<Map<String, Object>> getBookingStatusToBooking(@Param("id") Long id, @Param("date") String date, @Param("startTime") String startTime);
	
}
