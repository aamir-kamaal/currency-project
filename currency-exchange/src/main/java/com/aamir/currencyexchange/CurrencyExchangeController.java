package com.aamir.currencyexchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


    @RestController
    public class CurrencyExchangeController {

        @Autowired
        private Environment environment;
        @Autowired
        private ExchangeValueRepository repository;

        @GetMapping("/currency-exchange/from/{from}/to/{to}")
        public CurrencyExchange retrieveExchangeValue
                (@PathVariable String from, @PathVariable String to){

            CurrencyExchange exchangeValue =
                    repository.findByFromAndTo(from, to);

            exchangeValue.setPort(environment.getProperty("local.server.port"));
            System.out.println("currency-exchange called");

            return exchangeValue;
        }
    }

