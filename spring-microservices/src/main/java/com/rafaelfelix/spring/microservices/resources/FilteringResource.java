package com.rafaelfelix.spring.microservices.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rafaelfelix.spring.microservices.dto.FilterDTO;

@RestController
@RequestMapping(value = "/filter")
public class FilteringResource {
	
	@GetMapping("")
	public MappingJacksonValue doFilter() {
		FilterDTO filterDTO = new FilterDTO("value1", "value2", "value3");
		
		SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("DTOFilter", propertyFilter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(filterDTO);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	@GetMapping("/list")
	public MappingJacksonValue doFilterList() {
		List<FilterDTO> filterList = Arrays.asList(new FilterDTO("value12", "value22", "value32"), 
				new FilterDTO("value13", "value23", "value33"));
		
		SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("DTOFilter", propertyFilter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(filterList);
		mapping.setFilters(filters);
		
		return mapping;
	}
}
