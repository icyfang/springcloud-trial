package com.example.ribbonconsumer.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Hodur
 * @since 2021-04-24
 */
@RestController
public class BusinessController {

    @Autowired
    RestTemplate restTemplate;

    String serviceName = "business-service";

    @GetMapping("/business/instanceName")
    public String getName() {
        ResponseEntity<String> forEntity = restTemplate
                .getForEntity("http://" + serviceName + "/service/name", String.class);
        return forEntity.getBody();
    }
}
