package com.aamir.currencyexchange;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExchangeValueRepository extends
        JpaRepository<CurrencyExchange, Long>{
    CurrencyExchange findByFromAndTo(String from, String to);
}