package com.capstone.bhs.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class CommonFunction<T> {

	@SuppressWarnings("unchecked")
	public List<T> convertListQueryResultToDTO(List<Map<String, Object>> queryData) {
		List<T> result = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper().registerModule(new ParameterNamesModule())
				.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
		for (Map<String, Object> res : queryData) {
			T obj = (T) mapper.convertValue(res, Object.class);
			result.add(obj);
		}
		return result;
	}
}
