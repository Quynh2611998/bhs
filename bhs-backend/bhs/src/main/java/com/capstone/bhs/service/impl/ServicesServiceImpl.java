package com.capstone.bhs.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.capstone.bhs.common.CommonFunction;
import com.capstone.bhs.model.dto.QueryCategoryDTO;
import com.capstone.bhs.model.dto.QueryCategoryHome;
import com.capstone.bhs.model.dto.QueryOptionDTO;
import com.capstone.bhs.model.dto.QueryServiceDTO;
import com.capstone.bhs.model.dto.ServicesDTO;
import com.capstone.bhs.model.entity.Services;
import com.capstone.bhs.model.vm.ServicesVM;
import com.capstone.bhs.repository.ServiceRepository;
import com.capstone.bhs.service.ServicesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

@Service
public class ServicesServiceImpl implements ServicesService {

	@SuppressWarnings("rawtypes")
	private CommonFunction commonFunction = new CommonFunction();
	@Autowired
	private ServiceRepository serviceRepository;

	@Override
	public List<Services> getAllListServices() {
		return serviceRepository.findAll();
	}

	@Override
	public Services createServices(ServicesVM servicesVM) {
		Optional<Services> name = serviceRepository.findOneByServiceName(servicesVM.getServiceName());
		if (name.isPresent())
			throw new RuntimeErrorException(null);

		Services services = new Services();
		services.setServiceName(servicesVM.getServiceName());
		services.setCreatedDate(Instant.now());
		services.setCreatedBy(servicesVM.getCreatedBy());
		services.setModifiedDate(Instant.now());
		services.setModifiedBy(servicesVM.getModifiedBy());
		services.setCategoryId(servicesVM.getCategoryId());
		serviceRepository.save(services);
		return services;
	}

	@Override
	public Optional<Services> updateServices(ServicesVM servicesVM, Long id) {
		return serviceRepository.findOneById(id).map(user -> {
			user.setServiceName(servicesVM.getServiceName());
			user.setCategoryId(servicesVM.getCategoryId());
			user.setCreatedBy(servicesVM.getCreatedBy());
			user.setModifiedBy(servicesVM.getModifiedBy());
			serviceRepository.save(user);
			return user;
		});
	}

	@Override
	public void delete(long[] ids) {
		for (long item : ids) {
			serviceRepository.deleteById(item);
		}
	}

	@Override
	public Optional<Services> getServiceById(Long id) {
		return serviceRepository.findById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServicesDTO> getListAllJoinServices() {
		List<Map<String, Object>> queryData = serviceRepository.lstDetailService();
		return commonFunction.convertListQueryResultToDTO(queryData);
	}

	@Override
	public Object getAllServiceByCategoryId(Long id, Pageable pageable) {
		Page<List<Map<String, Object>>> queryData = serviceRepository.getAllServiceByCategoryId(id, pageable);
		return queryData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getOptionAndCategoryAndService() {
		List<Map<String, Object>> queryData = serviceRepository.getOptionAndCategoryByServiceId();
		List<QueryCategoryHome> result = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper().registerModule(new ParameterNamesModule())
				.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
		for (Map<String, Object> res : queryData) {
			QueryCategoryHome obj = mapper.convertValue(res, QueryCategoryHome.class);
			result.add(obj);
		}
		Map<String, Map<String, List<QueryCategoryHome>>> mapCategoryName = result.stream()
				.collect(Collectors.groupingBy(QueryCategoryHome::getCategoryName,
						Collectors.groupingBy(QueryCategoryHome::getServiceName)));
		@SuppressWarnings("rawtypes")
		List<QueryCategoryDTO> lst = new ArrayList();

		for (String categoryName : mapCategoryName.keySet()) {
			QueryCategoryDTO res = new QueryCategoryDTO();
			res.setCategoryName(categoryName);
			res.setServices(new ArrayList<QueryServiceDTO>());
			for (String serviceName : mapCategoryName.get(categoryName).keySet()) {
				QueryServiceDTO s = new QueryServiceDTO();
				List<QueryOptionDTO> lstOptions = new ArrayList<>();
				List<QueryCategoryHome> option = mapCategoryName.get(categoryName).get(serviceName);
				for (QueryCategoryHome obj : option) {
					QueryOptionDTO tmp = new QueryOptionDTO();
					tmp.setOptionId(obj.getOptionId());
					tmp.setOptionName(obj.getOptionName());
					lstOptions.add(tmp);
				}
				s.setServiceName(serviceName);
				s.setOptions(lstOptions);
				res.getServices().add(s);
			}
			lst.add(res);
		}
		return lst;
	}

	@Override
	public void deleteService(Long id) {
		serviceRepository.deleteById(id);

	}

}
