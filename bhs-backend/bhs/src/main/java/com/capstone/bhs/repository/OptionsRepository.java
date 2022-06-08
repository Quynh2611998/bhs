package com.capstone.bhs.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstone.bhs.model.entity.Options;

@Repository
public interface OptionsRepository extends JpaRepository<Options, Long> {

	Optional<Options> findOneById(Long id);

	Optional<Options> findOneByOptionName(String name);

	@Query(value = " SELECT op.id AS id, op.optionName AS optionName, op.duration AS duration, op.price AS price, MAX(im.srcImage) AS src, MAX(im.altImage) AS alt FROM Options op "
			+ "LEFT JOIN Images im ON op.id = im.optionId "
			+ "WHERE (op.optionName LIKE CONCAT('%', :keyword, '%') OR :keyword = '') "
			+ " GROUP BY op.id, op.optionName, op.duration, op.price"
			+ " ORDER BY LENGTH(op.optionName) ASC, op.modifiedDate DESC")
	List<Map<String, Object>> searchOptionByKeyword(@Param("keyword") String keyword);
	
	@Query(value = " SELECT op.id AS optionId, op.optionName AS optionName, sv.serviceName AS serviceName, op.duration AS duration, op.price AS price, MAX(im.srcImage) AS src, MAX(im.altImage) AS alt FROM Options op LEFT JOIN Images im ON im.optionId = op.id LEFT JOIN Services sv ON sv.id = op.serviceId "
			+ " GROUP BY op.id, op.optionName, op.duration, op.price, sv.serviceName ")
	Page <List<Map<String, Object>>> ViewOptionAtHomePage(Pageable pageable);
	
	@Query(value = " SELECT op.id AS optionId, op.optionName AS optionName, sv.serviceName AS serviceName, op.duration AS duration, op.price AS price FROM Options op LEFT JOIN Services sv ON sv.id = op.serviceId WHERE op.id = :id ")
	List<Map<String, Object>> getDetailOneOptionById(@Param("id") Long id);


	@Query(value = " SELECT "
			+ " o.id, o.option_name AS optionName, s.service_name AS serviceName, c.category_name AS categoryName "
			+ " FROM options o " + " JOIN services s " + " ON o.service_id = s.id " + " JOIN categories c "
			+ " ON s.categories_id = c.id ", nativeQuery = true)
	List<Map<String, Object>> searchOptionDTOByKeyword(@Param("keyword") String keyword);

	@Query(value = "SELECT"
			+ "	o.id AS id,o.`option_name` AS optionName, o.`service_id` AS serviceId, s.`service_name` AS serviceName, o.price AS price, o.duration AS duration,"
			+ "	o.created_date AS createdDate,o.created_by AS createdBy,o.modified_date AS modifiedDate,o.modified_by AS modifiedBy"
			+ "	FROM `options` o LEFT JOIN `services` s ON o.service_id = s.id", nativeQuery = true)
	List<Map<String, Object>> lstDetailOptions();
	
	@Query(value=" SELECT "                      + 
			" o.`id` AS id, "                    + 
			" o.`option_name` AS optionName, "   + 
			" o.`price` AS price, "              + 
			" o.`duration` AS duration, "        + 
			" s.`service_name` AS serviceName, " + 
			" MAX(i.`image_src`) AS src, "       +
			" MAX(i.`alt`) AS alt, " 			 +
			" o.`created_by` AS createdBy, " 	 + 
			" o.`created_date` AS createdDate, " + 
			" o.`modified_by` AS modifiedBy, "   + 
			" o.`modified_date` AS modifiedDate "+ 
			" FROM `options` o " 				 + 
			" LEFT JOIN `option_image` i " 		 + 
			" ON i.`option_id` = o.`id` "		 +
			" LEFT JOIN `services` s " 			 + 
			" ON s.`id` = o.`service_id` "
			+ " GROUP BY "
			+ " o.`id`, "
			+ " o.`option_name`, "
			+ " o.`price`, "
			+ " o.`duration`, "
			+ " s.`service_name`, "
			+ " o.`created_by`, "
			+ " o.`created_date`, "
			+ " o.`modified_by`, "
			+ " o.`modified_date` " , 
			countQuery = " SELECT COUNT(*) FROM (	 SELECT "        + 
					" o.`id` AS id, "                    + 
					" o.`option_name` AS optionName, "   + 
					" o.`price` AS price, "              + 
					" o.`duration` AS duration, "        + 
					" s.`service_name` AS serviceName, " + 
					" MAX(i.`image_src`) AS src, "       +
					" MAX(i.`alt`) AS alt, " 			 +
					" o.`created_by` AS createdBy, " 	 + 
					" o.`created_date` AS createdDate, " + 
					" o.`modified_by` AS modifiedBy, "   + 
					" o.`modified_date` AS modifiedDate "+ 
					" FROM `options` o " 				 + 
					" LEFT JOIN `option_image` i " 		 + 
					" ON i.`option_id` = o.`id` "		 +
					" LEFT JOIN `services` s " 			 + 
					" ON s.`id` = o.`service_id` "
					+ " GROUP BY "
					+ " o.`id`, "
					+ " o.`option_name`, "
					+ " o.`price`, "
					+ " o.`duration`, "
					+ " s.`service_name`, "
					+ " o.`created_by`, "
					+ " o.`created_date`, "
					+ " o.`modified_by`, "
					+ " o.`modified_date` "
					+ ") a", nativeQuery = true)
	Page<List<Map<String, Object>>> lstAllOptionImagePage(Pageable pageable);
	
	
	
	@Query(value=" SELECT "                      + 
			" o.`id` AS id, "                    + 
			"s.id AS serviceId, "				 +
			"s.categories_id AS categoryId, "	 +
			" o.`option_name` AS optionName, "   + 
			" o.`price` AS price, "              + 
			" o.`duration` AS duration, "        + 
			" s.`service_name` AS serviceName, " + 
			" MAX(i.`image_src`) AS src, "       +
			" MAX(i.`alt`) AS alt, " 			 +
			" o.`created_by` AS createdBy, " 	 + 
			" o.`created_date` AS createdDate, " + 
			" o.`modified_by` AS modifiedBy, "   + 
			" o.`modified_date` AS modifiedDate "+ 
			" FROM `options` o " 				 + 
			" LEFT JOIN `option_image` i " 		 + 
			" ON i.`option_id` = o.`id` "		 +
			" LEFT JOIN `services` s " 			 + 
			" ON s.`id` = o.`service_id` "		 +
			" WHERE s.`id` = :serviceId " 		 
			+ " GROUP BY "
			+ " o.`id`, "
			+ "s.id, "
			+ "s.categories_id, "	 
			+ " o.`option_name`, "
			+ " o.`price`, "
			+ " o.`duration`, "
			+ " s.`service_name`, "
			+ " o.`created_by`, "
			+ " o.`created_date`, "
			+ " o.`modified_by`, "
			+ " o.`modified_date` " , 
			countQuery = " SELECT COUNT(*) FROM ( "
					+
					" SELECT "                      + 
					" o.`id` AS id, "                    +
					"s.id AS serviceId, "				 +
					"s.categories_id AS categoryId, "	 +
					" o.`option_name` AS optionName, "   + 
					" o.`price` AS price, "              + 
					" o.`duration` AS duration, "        + 
					" s.`service_name` AS serviceName, " + 
					" MAX(i.`image_src`) AS src, "       +
					" MAX(i.`alt`) AS alt, " 			 +
					" o.`created_by` AS createdBy, " 	 + 
					" o.`created_date` AS createdDate, " + 
					" o.`modified_by` AS modifiedBy, "   + 
					" o.`modified_date` AS modifiedDate "+ 
					" FROM `options` o " 				 + 
					" LEFT JOIN `option_image` i " 		 + 
					" ON i.`option_id` = o.`id` "		 +
					" LEFT JOIN `services` s " 			 + 
					" ON s.`id` = o.`service_id` "		 +
					" WHERE s.`id` = :serviceId " 		 
					+ " GROUP BY "
					+ " o.`id`, "
					+ "s.id, "
					+ "s.categories_id, "
					+ " o.`option_name`, "
					+ " o.`price`, "
					+ " o.`duration`, "
					+ " s.`service_name`, "
					+ " o.`created_by`, "
					+ " o.`created_date`, "
					+ " o.`modified_by`, "
					+ " o.`modified_date` "
				  + ")  a", nativeQuery = true)
	Page<List<Map<String, Object>>> getAllOptionByServiceId(@Param("serviceId") Long id, Pageable pageable);

	@Query(value=" SELECT "                      + 
			" o.`id` AS optionId, "                    + 
			"s.id AS serviceId, "				 +
			"s.categories_id AS categoryId, "	 +
			" o.`option_name` AS optionName, "   + 
			" o.`price` AS price, "              + 
			" o.`duration` AS duration, "        + 
			" s.`service_name` AS serviceName, " + 
			" o.`created_by` AS createdBy, " 	 + 
			" o.`created_date` AS createdDate, " + 
			" o.`modified_by` AS modifiedBy, "   + 
			" o.`modified_date` AS modifiedDate "+ 
			" FROM `options` o " 				 + 
			" LEFT JOIN `services` s " 			 + 
			" ON s.`id` = o.`service_id` "		 +
			" WHERE o.`id` = :optionId ", nativeQuery = true) 		
	List<Map<String, Object>> getOptionByOptionId(@Param("optionId") Long id);
	
	@Query(value=" SELECT " 							+ 
			" o.`id` AS id, " 							+ 
			" o.`option_name` AS optionName, "			+ 
			" o.`price` AS price, " 					+ 
			" o.`duration` AS duration, " 				+ 
			" s.`service_name` AS serviceName, " 		+ 
			" o.`created_by` AS createdBy, " 			+ 
			" o.`created_date` AS createdDate, " 		+ 
			" o.`modified_by` AS modifiedBy, " 			+ 
			" o.`modified_date` AS modifiedDate " 		+ 
			" FROM `options` o " 						+ 
			" LEFT JOIN `services` s " 					+ 
			" ON s.`id` = o.`service_id` ", nativeQuery = true)
	List<Map<String, Object>> lstViewAllOption();
	
	@Query(value = "DELETE FROM `options` WHERE `id` = ?", nativeQuery = true)
	Object deleteOption(@Param("id") Long id);
	
}
