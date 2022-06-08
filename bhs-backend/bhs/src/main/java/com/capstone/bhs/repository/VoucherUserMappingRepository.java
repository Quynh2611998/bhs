package com.capstone.bhs.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstone.bhs.model.entity.VoucherUserMapping;

@Repository
public interface VoucherUserMappingRepository extends JpaRepository<VoucherUserMapping, Long> {

	Optional<VoucherUserMapping> findOneById(Long id);

	Optional<VoucherUserMapping> findOneByUserId(Long userId);
	
	Optional<VoucherUserMapping> findOneByVoucherUserId(Long voucherUserId);
	
	@Query(value = " SELECT vu.id AS voucherId, vc.id AS voucherUserMappingId, vu.name AS voucherUserName, vu.discount AS discount, vu.dayStart AS dayStart, vu.dayExpire AS dayExpire, vu.isActived AS isActived FROM VoucherUserMapping vc LEFT JOIN VoucherUser vu ON vu.id = vc.voucherUserId LEFT JOIN AdUser ad ON ad.id = vc.userId WHERE ad.id = :id ")
	List<Map<String, Object>> findOneVoucherUserByUserId(@Param("id") Long id);
	
	@Query(value = "SELECT vum.id AS id, vum.userId AS userId, au.username AS userName, vum.voucherUserId AS voucherUserId, " + 
			"vu.name AS voucherUserName, vu.dayStart AS dayStart, vu.dayExpire AS dayExpire, vu.discount AS discount, vu.isActived AS isActived FROM VoucherUserMapping vum " + 
			"LEFT JOIN AdUser au ON au.id = vum.userId " + 
			"LEFT JOIN VoucherUser vu ON vu.id = vum.voucherUserId " + 
			"WHERE vum.userId = :userId AND vu.isActived = 1")
	List<Map<String, Object>> findVoucherUserIsActived(@Param("userId") Long id);

}
