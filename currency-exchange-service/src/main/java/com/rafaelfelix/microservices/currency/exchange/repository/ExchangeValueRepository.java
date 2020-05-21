package com.rafaelfelix.microservices.currency.exchange.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelfelix.microservices.currency.exchange.resources.dto.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long>{

	Optional<ExchangeValue> findByFromAndTo(String from, String to);
}
