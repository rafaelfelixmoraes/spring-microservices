package com.rafaelfelix.microservices.currency.exchange.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rafaelfelix.microservices.currency.exchange.repository.ExchangeValueRepository;
import com.rafaelfelix.microservices.currency.exchange.resources.dto.ExchangeValue;

@Component
public class ExchangeValueService {

	@Autowired
	private ExchangeValueRepository repo;
	
	public Optional<ExchangeValue> findByFromAndTo(String from, String to){
		return repo.findByFromAndTo(from, to);
	}
}
