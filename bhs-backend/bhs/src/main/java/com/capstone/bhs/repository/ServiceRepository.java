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

import com.capstone.bhs.model.entity.Services;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Long> {

	Optional<Services> findOneById(Long id);

	Optional<Services> findOneByServiceName(String name);

	@Query(value =  "SELECT  "
			+  "s.id AS id,s.service_name AS serviceName,s.categories_id AS categoryId,c.category_name AS categoryName, "
			+  "s.created_date AS createdDate,s.created_by AS createdBy,s.modified_date AS modifedDate,s.modified_by AS modifedBy  "
			+  "FROM services s  " +  "LEFT JOIN categories c  " +  "ON s.categories_id = c.id ", nativeQuery = true)
	List<Map<String, Object>> lstDetailService();
	
	@Query(value= " SELECT  " + 
			 "s.`id` AS id,  " + 
			 "c.id AS categoryId, "	+
			 "s.`service_name` AS serviceName,  " + 
			 "c.`category_name` AS categoryName,  " + 
			 "s.`created_by` AS createdBy,  " + 
			 "s.`created_date` AS createdDate,  " + 
			 "s.`modified_by` AS modifiedBy,  " + 
			 "s.`modified_date` AS modifiedDate  " + 
			 "FROM `services` s  " + 
			 "LEFT JOIN `categories` c  " + 
			 "ON c.`id` = s.`categories_id`  " + 
			 "WHERE c.`id` = :categoryId  " , 
			countQuery =  " SELECT COUNT(*) FROM (	 SELECT  " + 
					 "s.`id` AS id,  " + 
					 "c.id AS categoryId, "	+
					 "s.`service_name` AS serviceName,  " + 
					 "c.`category_name` AS categoryName,  " + 
					 "s.`created_by` AS createdBy,  " + 
					 "s.`created_date` AS createdDate,  " + 
					 "s.`modified_by` AS modifiedBy,  " + 
					 "s.`modified_date` AS modifiedDate  " + 
					 "FROM `services` s  " + 
					 "LEFT JOIN `categories` c  " + 
					 "ON c.`id` = s.`categories_id`  " + 
					 "WHERE c.`id` = :categoryId  a ", nativeQuery = true)
	Page<List<Map<String, Object>>> getAllServiceByCategoryId(@Param( "categoryId") Long id, Pageable pageable);
	
	@Query(value =  "SELECT  " 		+ 
			
			 "b.`category_name` AS categoryName, "  + 
			
			 "a.`service_name` AS serviceName, "	+ 
			 "c.`id` AS optionId, " 			+ 
			 "c.`option_name` AS optionName " 	+ 
			 "FROM  `services` a " + 
			 "LEFT JOIN `categories` b ON a.`categories_id` = b.`id` " + 
			 "LEFT JOIN `options` c ON c.`service_id` = a.`id` ", nativeQuery = true)
	List<Map<String, Object>> getOptionAndCategoryByServiceId();
}
