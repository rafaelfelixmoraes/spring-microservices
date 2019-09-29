package com.rafaelfelix.spring.microservices.dto;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
//@JsonIgnoreProperties(value = {"field2"})
@JsonFilter(value = "DTOFilter")
public class FilterDTO {
	
	private String field1;
	private String field2;
	
	//@JsonIgnore
	private String field3;
}
