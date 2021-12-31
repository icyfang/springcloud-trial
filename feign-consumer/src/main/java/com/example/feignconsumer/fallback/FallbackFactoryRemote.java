package com.example.feignconsumer.fallback;

import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Hodur
 * @date 2021/04/02
 */
@FeignClient(name = "business-service"
        , contextId = "factory"
        , fallbackFactory = FallbackFactoryRemote.HystrixClientFallbackFactory.class
)
public interface FallbackFactoryRemote {

    @GetMapping(value = "/business/fallback")
    String fallback();

    @Component
    class HystrixClientFallbackFactory implements FallbackFactory<FallbackFactoryRemote> {
        @Override
        public FallbackFactoryRemote create(Throwable cause) {
            return () -> "fallbackFactory";
        }
    }
}
