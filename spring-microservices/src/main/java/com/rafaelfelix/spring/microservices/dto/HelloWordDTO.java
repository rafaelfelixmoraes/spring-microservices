package com.rafaelfelix.spring.microservices.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloWordDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public HelloWordDTO(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWordDTO [message=" + message + "]";
	}

}
