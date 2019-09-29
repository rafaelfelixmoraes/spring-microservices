package com.rafaelfelix.spring.microservices.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelfelix.spring.microservices.dto.Name;
import com.rafaelfelix.spring.microservices.dto.PersonV1;
import com.rafaelfelix.spring.microservices.dto.PersonV2;

@RestController
public class VersioningResource {
	
	@GetMapping("/v1/person")
	public PersonV1 urlPerson1() {
		return new PersonV1("José Maria");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 urlPerson2() {
		return new PersonV2(new Name("José", "Maria"));
	}
	
	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 paramPerson1() {
		return new PersonV1("José Maria");
	}
	
	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 paramPerson2() {
		return new PersonV2(new Name("José", "Maria"));
	}
	
	@GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 headerPerson1() {
		return new PersonV1("José Maria");
	}
	
	@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 headerPerson2() {
		return new PersonV2(new Name("José", "Maria"));
	}
	
	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 producesPerson1() {
		return new PersonV1("José Maria");
	}
	
	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 producesPerson2() {
		return new PersonV2(new Name("José", "Maria"));
	}
}
