package com.rafaelfelix.microservices.currency.exchange.resources;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelfelix.microservices.currency.exchange.resources.dto.ExchangeValue;
import com.rafaelfelix.microservices.currency.exchange.services.ExchangeValueService;

@RestController
@RequestMapping("currency-exchange")
public class CurrencyExchangeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueService service;
	
	@GetMapping("/from/{from}/to/{to}")
	public ResponseEntity<?> retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		Optional<ExchangeValue> exchangeValueEntity = service.findByFromAndTo(from, to);
		if(!exchangeValueEntity.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		ExchangeValue exchangeValue = exchangeValueEntity.get();
		exchangeValue.setPort(Integer.decode(environment.getRequiredProperty("local.server.port")));
		
		logger.info("{}", exchangeValue);
		
		return ResponseEntity.ok(exchangeValue);
	}
}
