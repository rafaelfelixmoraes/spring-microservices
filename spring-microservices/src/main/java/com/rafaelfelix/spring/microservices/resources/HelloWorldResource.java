package com.rafaelfelix.spring.microservices.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelfelix.spring.microservices.dto.HelloWordDTO;

@RestController
@RequestMapping(value = "/hello")
public class HelloWorldResource {

	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	public ResponseEntity<?> helloWorld(){
		return ResponseEntity.ok("Hello World!!");
	}
	
	@GetMapping("/dto")
	public ResponseEntity<HelloWordDTO> helloWorldDTO(){
		return ResponseEntity.ok(new HelloWordDTO("Hello World 2.0"));
	}
	
	@GetMapping("/path/{name}")
	public ResponseEntity<HelloWordDTO> helloWorldPathVariable(@PathVariable String name){
		return ResponseEntity.ok(new HelloWordDTO(String.format("Hello World, %s", name)));
	}
	
	@GetMapping("/hello-world-inter")
	public ResponseEntity<?> helloWorldInternationalized(){
		return ResponseEntity.ok(messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale()));
	}
}
