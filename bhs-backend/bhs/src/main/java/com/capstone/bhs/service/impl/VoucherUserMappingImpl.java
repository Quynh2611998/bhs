package com.capstone.bhs.service.impl;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.capstone.bhs.common.CommonFunction;
import com.capstone.bhs.model.dto.VoucherUserMappingDTO;
import com.capstone.bhs.model.entity.VoucherUser;
import com.capstone.bhs.model.entity.VoucherUserMapping;
import com.capstone.bhs.model.vm.VoucherUserVM;
import com.capstone.bhs.repository.AdUserRepository;
import com.capstone.bhs.repository.VoucherUserMappingRepository;
import com.capstone.bhs.repository.VoucherUserRepository;
import com.capstone.bhs.service.VoucherUserMappingService;

@Service
public class VoucherUserMappingImpl implements VoucherUserMappingService {

	@SuppressWarnings("rawtypes")
	private CommonFunction commonFunction = new CommonFunction();

	@Autowired
	private VoucherUserRepository voucherUserRepository;

	@Autowired
	private VoucherUserMappingRepository voucherUserMappingRepository;

	@Override
	public VoucherUser createVoucherUser(VoucherUserVM voucherUserVM) {
		VoucherUser voucherUser = new VoucherUser();

		voucherUser.setName(voucherUserVM.getName());
		voucherUser.setDiscount(voucherUserVM.getDiscount());
		voucherUser.setDayStart(voucherUserVM.getDayStart());
		voucherUser.setDayExpire(voucherUserVM.getDayExpire());
		voucherUser.setActived(voucherUserVM.isActived());
		voucherUser.setCreatedDate(Instant.now());
		voucherUser.setCreatedBy(voucherUserVM.getCreatedBy());
		voucherUser.setModifiedDate(Instant.now());
		voucherUser.setModifiedBy(voucherUserVM.getModifiedBy());
		voucherUserRepository.save(voucherUser);

		for (Long userId : voucherUserVM.getUserId()) {

			VoucherUserMapping voucherUserMapping = new VoucherUserMapping();
			voucherUserMapping.setUserId(userId);
			voucherUserMapping.setVoucherUserId(voucherUser.getId());
			voucherUserMappingRepository.save(voucherUserMapping);
		}
		return voucherUser;
	}

	@Override
	public Optional<VoucherUser> updateVoucherUser(VoucherUserVM voucherUserVM, Long id) {
		return voucherUserRepository.findOneById(id).map(voucher -> {
			voucher.setName(voucherUserVM.getName());
			voucher.setDiscount(voucherUserVM.getDiscount());
			voucher.setDayStart(voucherUserVM.getDayStart());
			voucher.setDayExpire(voucherUserVM.getDayExpire());
			voucher.setActived(voucherUserVM.isActived());
			voucher.setCreatedDate(Instant.now());
			voucher.setModifiedDate(Instant.now());
			voucher.setModifiedBy(voucherUserVM.getModifiedBy());
			voucherUserRepository.save(voucher);
			for (Long userId : voucherUserVM.getUserId()) {

				VoucherUserMapping voucherUserMapping = new VoucherUserMapping();

				voucherUserMapping.setUserId(userId);
				voucherUserMapping.setVoucherUserId(voucher.getId());
				voucherUserMappingRepository.save(voucherUserMapping);

			}
			return voucher;
		});
	}

	@Override
	public void deleteVoucherUser(long[] ids) {
		for (long item : ids) {
			voucherUserRepository.deleteById(item);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VoucherUserMappingDTO> getAllListVoucherUserMapping() {
		List<Map<String, Object>> queryData = voucherUserRepository.lstDetailVoucherUserMapping();
		return commonFunction.convertListQueryResultToDTO(queryData);
	}

	@Override
	public Optional<VoucherUser> getVoucherUserById(Long id) {
		return voucherUserRepository.findOneById(id);
	}

	@Override
	public Optional<VoucherUser> activeVoucherUser(VoucherUserVM voucherUserVM, Long id) {
		return voucherUserRepository.findOneById(id).map(voucher -> {
			String pattern = "yyyy-MM-dd";
			String dateStart = new SimpleDateFormat(pattern).format(new Date());
			Object start = findByDayStart(dateStart, id);

			String dateExpire = new SimpleDateFormat(pattern).format(new Date());
			Object expire = findByDayExpire(dateExpire, id);

			if (voucherUserRepository.findByDay(String.valueOf(start), String.valueOf(expire)) != null) {
				boolean isActived = true;
				voucher.setActived(isActived);
			}
			voucherUserRepository.save(voucher);
			return voucher;
		});
	}

	@Override
	public void deleteVoucherUserMapping(long[] ids) {
		for (long item : ids) {
			voucherUserMappingRepository.deleteById(item);
		}

	}

	@Override
	public Page<List<Map<String, Object>>> findAllVoucherUser(Pageable pageable) {
		return voucherUserRepository.findAllVoucherUser(pageable);
	}

	@Override
	public List<Map<String, Object>> findOneVoucherUserByUserId(Long id) {
		return voucherUserMappingRepository.findOneVoucherUserByUserId(id);
	}

	@Override
	public List<Map<String, Object>> findVoucherUserIsActived(Long id) {
		return voucherUserMappingRepository.findVoucherUserIsActived(id);
	}

//	@Scheduled(cron = "0 7 1 * * *")
//	public void scheduleTaskUsingCronExpression() throws NonUniqueResultException {
//		String pattern = "yyyy-MM-dd";
//		String dateInString = new SimpleDateFormat(pattern).format(new Date());
//		Object date = findByDayStart(dateInString);
//		boolean isActived = true;
//		VoucherUser voucherUser = new VoucherUser();
//		if (date.equals(dateInString)) {
//			voucherUser.setActived(isActived);
//			System.out.println("Actived Success");
//		}
//		voucherUserRepository.save(voucherUser);
//	}
//

//	@Override
//	public Object findByDay(String dayStart, String dayExpire) {
//		Object startDay = findByDayStart(dayStart);
//		Object expireDay = findByDayExpire(dayExpire);
//		return voucherUserRepository.findByDay(String.valueOf(startDay), String.valueOf(expireDay));
//	}

	@Override
	public Object findByDayStart(String dayStart, Long id) {
		return voucherUserRepository.findByDayStart(dayStart, id);
	}

	@Override
	public Object findByDayExpire(String dayExpire, Long id) {
		return voucherUserRepository.findByDayExpire(dayExpire, id);
	}

	@Autowired
	private AdUserRepository adUserRepository;

	@Override
	public Object findUserIdByUserName(String username) {
		return adUserRepository.findUserIdByUserName(username);
	}

	@Override
	public Object findByDayAndUserId(String dayStart, String dayExpire, Long id) {
		return voucherUserRepository.findByDayAndUserId(dayStart, dayExpire, id);
	}

	@Override
	public Object findUserIdByDate(String dayStart, String dayExpire, Long id) {
		return voucherUserRepository.findUserIdByDate(dayStart, dayExpire, id);
	}

}
