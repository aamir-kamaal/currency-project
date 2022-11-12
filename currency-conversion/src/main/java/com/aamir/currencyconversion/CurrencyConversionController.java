package com.aamir.currencyconversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {
    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{qty}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to,@PathVariable BigDecimal qty) {

        CurrencyConversion cc = currencyExchangeProxy.retrieveExchangeValue(from,to);

        return new CurrencyConversion(cc.getId(),
                from,
                to,
                qty,
                cc.getConversionMultiple(),
                qty.multiply(cc.getConversionMultiple()),
                cc.getEnvironment() + " feign");
    }
}
