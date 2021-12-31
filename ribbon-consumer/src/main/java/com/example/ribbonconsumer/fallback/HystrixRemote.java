package com.example.ribbonconsumer.fallback;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Hodur
 * @date 2021/04/24
 */
@Service
@Slf4j
public class HystrixRemote {

    @Value("${service-url.business-service}")
    String serviceName;

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultFallback")
    public String fallback() {
        ResponseEntity<String> forEntity = restTemplate
                .getForEntity(serviceName + "/business/fallback", String.class);
        return forEntity.getBody();
    }

    @HystrixCommand(fallbackMethod = "defaultException", ignoreExceptions = HttpServerErrorException.class)
    public String exception() {
        ResponseEntity<String> forEntity = restTemplate
                .getForEntity(serviceName + "/business/exception", String.class);
        return forEntity.getBody();
    }

    public String defaultFallback(Exception e) {
        log.error("request /business/fallback failed.");
        log.error("", e);
        return "business-service";
    }

    public String defaultException() {
        log.error("request /business/exception failed.");
        return "business-service";
    }
}
