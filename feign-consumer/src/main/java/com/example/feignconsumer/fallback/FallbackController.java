package com.example.feignconsumer.fallback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Hodur
 * @date 2021/04/23
 */
@Controller
public class FallbackController {

    @Autowired
    private FallbackFactoryRemote fallbackFactory;

    @Autowired
    private FallbackRemote fallback;

    @GetMapping("/fallback")
    public String fallback() {
        return fallback.fallback();
    }

    @GetMapping("/fallbackFactory")
    public String fallbackFactory() {
        return fallbackFactory.fallback();
    }
}
