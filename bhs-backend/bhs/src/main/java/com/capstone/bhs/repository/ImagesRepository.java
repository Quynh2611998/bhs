package com.capstone.bhs.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.bhs.model.entity.Images;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Long> {

	Optional<Images> findOneByImageId(Long id);
	
	Optional<Images> findOneByOptionId(long[] id);

	List<Images> findByOptionId(Long id);
	
	Optional<Images> findOneByOptionId(Long id);
	
	@Query(value=" SELECT i.`id` AS id, " + 
			" i.`image_src` AS src, " + 
			" i.`alt` AS alt, " + 
			" o.`option_name` AS optionName, " + 
			" i.`created_date` AS createdDate, " + 
			" i.`created_by` AS createdBy, " + 
			" i.`modified_date` AS modifiedDate, " + 
			" i.`modified_by` AS modifiedBy " + 
			" FROM `option_image` i " + 
			" LEFT JOIN `options` o " + 
			" ON o.`id` = i.`option_id` ",nativeQuery = true)
	List<Map<String, Object>> lstDetailOptionImage();

}
