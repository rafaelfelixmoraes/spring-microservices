package com.rafaelfelix.spring.microservices.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelfelix.spring.microservices.dto.HelloWordDTO;

@RestController
@RequestMapping(value = "/hello")
public class HelloWorldResource {

	@GetMapping
	public ResponseEntity<?> helloWorld(){
		return ResponseEntity.ok("Hello World!!");
	}
	
	@GetMapping("/dto")
	public ResponseEntity<HelloWordDTO> helloWorldDTO(){
		return ResponseEntity.ok(new HelloWordDTO("Hello World 2.0"));
	}
}
