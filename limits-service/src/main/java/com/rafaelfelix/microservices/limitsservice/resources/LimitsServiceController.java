package com.rafaelfelix.microservices.limitsservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rafaelfelix.microservices.limitsservice.config.Configuration;
import com.rafaelfelix.microservices.limitsservice.dto.LimitConfiguration;

@RestController
@RequestMapping("/limits")
public class LimitsServiceController {

	@Autowired
	private Configuration configuration;
	
	@GetMapping("")
	public LimitConfiguration retrieveLimitsFromConfigurations() {
		return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
	}
	
	@GetMapping("/fault-limits")
	@HystrixCommand(fallbackMethod = "fallbackRetrieveConfigurations")
	public LimitConfiguration retrieveConfigurations() {
		throw new RuntimeException("Not Avaible");
	}
	
	public LimitConfiguration fallbackRetrieveConfigurations() {
		return new LimitConfiguration(5000, 100);
	}
}
