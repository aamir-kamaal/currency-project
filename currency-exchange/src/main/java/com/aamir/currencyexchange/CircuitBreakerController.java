package com.aamir.currencyexchange;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {
    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    @Retry(name="sample-api",fallbackMethod = "hardCodedResponse")
    @RateLimiter(name="default")
    @Bulkhead(name="sample-api")
    public String sampleAPi()
    {
        logger.info("sample api call rcvd");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/dummy",String.class);
        return forEntity.toString();
    }

    public String hardCodedResponse(Exception e)
    {
        return "Server not available at this moment. We are checking";
    }
}
