package com.example.feignconsumer.fallback;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Hodur
 * @date 2021/03/17
 */
@FeignClient(name = "business-service"
        , contextId = "fallback"
        , fallback = FallbackRemote.FallbackFallback.class
)
public interface FallbackRemote {

    @GetMapping(value = "/business/fallback")
    String fallback();

    @Component
    class FallbackFallback implements FallbackRemote {

        @Override
        public String fallback() {
            return "fallback";
        }

    }
}
