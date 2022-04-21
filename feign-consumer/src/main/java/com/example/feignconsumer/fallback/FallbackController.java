package com.example.feignconsumer.fallback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hodur
 * @date 2021/04/23
 */
@RestController
public class FallbackController {

    @Autowired
    private FallbackFactoryRemote fallbackFactory;

    @Autowired
    private FallbackRemote fallback;


    @GetMapping("/fallback")
    public String fallback() {
        return fallback.fallback();
    }

    @GetMapping("/exception")
    public String exception() {
        return fallback.exception();
    }

    @GetMapping("/fallbackFactory")
    public String fallbackFactory() {
        return fallbackFactory.fallback();
    }

    @GetMapping("/fallbackFactory/exception")
    public String fallbackFactoryException() {
        return fallbackFactory.exception();
    }
}
