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

import com.capstone.bhs.model.entity.VoucherUser;

@Repository
public interface VoucherUserRepository extends JpaRepository<VoucherUser, Long>{

	Optional<VoucherUser> findOneById(Long id);
	
	@Query(value=" SELECT v.`id` AS id, " + 
			" u.`id` AS voucherUserId, " + 
			" u.`name` AS voucherUserName, " + 
			" a.`id` AS userId, " + 
			" a.`username` AS userName " + 
			" FROM `voucher_user_mapping` v " + 
			" LEFT JOIN `voucher_user` u " + 
			" ON u.`id` = v.`voucher_user_id` " + 
			" LEFT JOIN `ad_user` a " + 
			" ON a.`id` = v.`user_id` ", nativeQuery = true)
	List<Map<String, Object>> lstDetailVoucherUserMapping();
	
	@Query(value = "SELECT v.id AS id, v.name AS voucherUserName, v.discount AS discount, "
			+ " v.dayStart AS dayStart, v.dayExpire AS dayExpire, v.isActived  AS isActived, "
			+ " v.createdDate AS createdDate, v.createdBy AS createdBy, v.modifiedDate AS modifiedDate, "
			+ " v.modifiedBy AS modifiedBy FROM VoucherUser v  ")
	Page<List<Map<String, Object>>> findAllVoucherUser(Pageable pageable);
	
	@Query(value = "SELECT v.dayStart, v.dayExpire FROM VoucherUser v WHERE v.dayStart >= :dayStart AND v.dayExpire <= :dayExpire")
	Object findByDay(@Param("dayStart") String dayStart, @Param("dayExpire") String dayExpire);
	
	@Query(value = "SELECT v.dayStart FROM VoucherUser v WHERE v.dayStart <= :dayStart AND v.id = :id")
	Object findByDayStart(@Param("dayStart") String dayStart, @Param("id") Long id);
	
	@Query(value = "SELECT v.dayExpire FROM VoucherUser v WHERE v.dayStart <= :dayStart AND v.id = :id")
	Object findByDayExpire(@Param("dayStart") String dayExpire, @Param("id") Long id);
	
	@Query(value = "SELECT v.dayStart, v.dayExpire, ad.id FROM VoucherUser v " + 
			"LEFT JOIN VoucherUserMapping vum ON vum.voucherUserId = v.id " + 
			"LEFT JOIN AdUser ad ON ad.id = vum.userId " + 
			"WHERE v.dayStart >= :dayStart AND v.dayExpire <= :dayExpire AND ad.id = :id")
	Object findByDayAndUserId(@Param("dayStart") String dayStart, @Param("dayExpire") String dayExpire, @Param("id") Long id);
	
	@Query(value = "SELECT ad.id FROM VoucherUser v " + 
			"LEFT JOIN VoucherUserMapping vum ON vum.voucherUserId = v.id " + 
			"LEFT JOIN AdUser ad ON ad.id = vum.userId " + 
			"WHERE v.dayStart >= :dayStart AND v.dayExpire <= :dayExpire AND ad.id = :id")
	Object findUserIdByDate(@Param("dayStart") String dayStart, @Param("dayExpire") String dayExpire, @Param("id") Long id);
	

}
