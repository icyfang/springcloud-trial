package com.example.feignconsumer.fallback;

import com.example.feignconsumer.business.FeignConfig;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Shanghong Cai
 * @since 2021-04-02
 */
@FeignClient(name = "business-instance"
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
