package com.capstone.bhs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.capstone.bhs.model.dto.ServicesDTO;
import com.capstone.bhs.model.entity.Services;
import com.capstone.bhs.model.vm.ServicesVM;

public interface ServicesService {

	public List<Services> getAllListServices();

	public Services createServices(ServicesVM servicesVM);

	public Optional<Services> updateServices(ServicesVM servicesVM, Long id);

	void delete(long[] ids);

	public Optional<Services> getServiceById(Long id);

	public List<ServicesDTO> getListAllJoinServices();

	public Object getAllServiceByCategoryId(Long id, Pageable pageable);

	public Object getOptionAndCategoryAndService();

	public void deleteService(Long id);

}
