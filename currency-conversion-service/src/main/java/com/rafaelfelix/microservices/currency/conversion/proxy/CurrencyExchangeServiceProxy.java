package com.rafaelfelix.microservices.currency.conversion.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rafaelfelix.microservices.currency.conversion.resources.dto.CurrencyConversionDTO;

//@FeignClient(name = "currency-exchange-service", url = "localhost:8000/currency-exchange")
//@FeignClient(name = "currency-exchange-service")
@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionDTO retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
