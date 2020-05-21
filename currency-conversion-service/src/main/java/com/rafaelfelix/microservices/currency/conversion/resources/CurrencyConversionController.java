package com.rafaelfelix.microservices.currency.conversion.resources;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rafaelfelix.microservices.currency.conversion.proxy.CurrencyExchangeServiceProxy;
import com.rafaelfelix.microservices.currency.conversion.resources.dto.CurrencyConversionDTO;

@RestController
@RequestMapping("currency-conversion")
public class CurrencyConversionController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CurrencyExchangeServiceProxy currencyConversionProxy;
	
	private static final RestTemplate restTemplate = new RestTemplate();
	
	private static final String currencyExchangeURL = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";

	@GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
	public ResponseEntity<?> retriveConversionValue(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){
		
		Map<String, String> urlVariables = new HashMap<>();
		urlVariables.put("from", from);
		urlVariables.put("to", to);
		ResponseEntity<CurrencyConversionDTO> responseEntity = restTemplate.getForEntity(
				currencyExchangeURL, 
				CurrencyConversionDTO.class, 
				urlVariables);
		
		CurrencyConversionDTO currencyConversion = responseEntity.getBody();
		
		return ResponseEntity.ok(new CurrencyConversionDTO(
				currencyConversion.getId(), 
				from, 
				to, 
				currencyConversion.getConversionMultiple(), 
				quantity, 
				quantity.multiply(currencyConversion.getConversionMultiple()), 
				currencyConversion.getPort()));
	}
	
	@GetMapping("/feign/from/{from}/to/{to}/quantity/{quantity}")
	public ResponseEntity<?> retriveConversionValueFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){
		
		CurrencyConversionDTO currencyConversion = currencyConversionProxy.retrieveExchangeValue(from, to);
		
		logger.info("{}", currencyConversion);
		
		return ResponseEntity.ok(new CurrencyConversionDTO(
				currencyConversion.getId(), 
				from, 
				to, 
				currencyConversion.getConversionMultiple(), 
				quantity, 
				quantity.multiply(currencyConversion.getConversionMultiple()), 
				currencyConversion.getPort()));
	}
	
}
