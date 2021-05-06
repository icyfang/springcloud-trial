package com.example.ribbonconsumer.fallback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hodur
 * @date 2021-04-24
 */
@RestController
public class HystrixController {

    @Autowired
    HystrixRemote hystrixRemote;

    @GetMapping("/hystrix/fallback")
    public String fallback() {
        return hystrixRemote.fallback();
    }

    @GetMapping("/hystrix/exception")
    public String exception() {
        return hystrixRemote.exception();
    }
}
