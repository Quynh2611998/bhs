package com.capstone.bhs.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.capstone.bhs.model.dto.VoucherUserMappingDTO;
import com.capstone.bhs.model.entity.VoucherUser;
import com.capstone.bhs.model.vm.VoucherUserVM;

public interface VoucherUserMappingService {

	Page<List<Map<String, Object>>> findAllVoucherUser(Pageable pageable);

	public List<VoucherUserMappingDTO> getAllListVoucherUserMapping();

	public Optional<VoucherUser> getVoucherUserById(Long id);

	public VoucherUser createVoucherUser(VoucherUserVM voucherUserVM);

	public Optional<VoucherUser> updateVoucherUser(VoucherUserVM voucherUserVM, Long id);

	void deleteVoucherUser(long[] ids);

	public Optional<VoucherUser> activeVoucherUser(VoucherUserVM voucherUserVM, Long id);

	void deleteVoucherUserMapping(long[] ids);

	List<Map<String, Object>> findOneVoucherUserByUserId(Long id);

	List<Map<String, Object>> findVoucherUserIsActived(Long id);

//	public void scheduleTaskUsingCronExpression() throws NonUniqueResultException;
//	

	Object findByDayStart(String dayStart, Long id);

	Object findByDayExpire(String dayExpire, Long id);

	Object findUserIdByUserName(String username);

	Object findByDayAndUserId(String dayStart, String dayExpire, Long id);
	
	Object findUserIdByDate(String dayStart, String dayExpire, Long id);
}
