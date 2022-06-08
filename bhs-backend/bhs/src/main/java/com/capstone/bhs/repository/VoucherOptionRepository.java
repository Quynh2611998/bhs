package com.capstone.bhs.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstone.bhs.model.entity.VoucherOptions;

@Repository
public interface VoucherOptionRepository extends JpaRepository<VoucherOptions, Long> {

	Optional<VoucherOptions> findOneById(Long id);
	
	@Query(value=" SELECT v.id AS id, " + 
			" o.`id` AS voucherOptionId, " + 
			" o.`name` AS voucherOptionName, " + 
			" s.`id` AS optionId, " + 
			" s.`option_name` AS optionName " + 
			" FROM `voucher_option_mapping` v " + 
			" LEFT JOIN `voucher_options` o " + 
			" ON o.`id` = v.`voucher_option_id` " + 
			" LEFT JOIN `options` s " + 
			" ON s.`id` = v.`option_id` ", nativeQuery = true)
	List<Map<String, Object>> lstDetailVoucherOptionMapping();
	
	@Query(value = " SELECT vom.`id` AS id, " + 
			" vo.`name` AS voucherOptionName, " + 
			" o.`option_name` AS optionName " + 
			" FROM `voucher_option_mapping` vom " + 
			" LEFT JOIN `voucher_options` vo " + 
			" ON vo.`id` = vom.`voucher_option_id` " + 
			" LEFT JOIN `options` o " + 
			" ON o.`id` = vom.`option_id` " + 
			" WHERE vo.`id` = ? ", nativeQuery = true)
	List<Map<String, Object>> getAllOptionByVoucherOptionId(@Param("voucherOptionId") Long voucherOptionId);
	
	@Query(value = " SELECT vom.`id` AS id, " + 
			" vo.`name` AS voucherOptionName, " + 
			" o.`option_name` AS optionName " + 
			" FROM `voucher_option_mapping` vom " + 
			" LEFT JOIN `voucher_options` vo " + 
			" ON vo.`id` = vom.`voucher_option_id` " + 
			" LEFT JOIN `options` o " + 
			" ON o.`id` = vom.`option_id` " + 
			" WHERE o.`id` = ? ", nativeQuery = true)
	List<Map<String, Object>> getAllVoucherOptionByOptionId(@Param("optionId") Long optionId);

}
