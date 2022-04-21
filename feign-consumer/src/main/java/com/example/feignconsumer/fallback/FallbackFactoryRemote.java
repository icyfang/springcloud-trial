package com.example.feignconsumer.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import feign.hystrix.FallbackFactory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Hodur
 * @date 2021/04/02
 */
@FeignClient(name = "business-service"
        , contextId = "factory"
        , path = "/example"
        , fallbackFactory = FallbackFactoryRemote.HystrixClientFallbackFactory.class
)
public interface FallbackFactoryRemote {

    @GetMapping(value = "/business/fallback")
    String fallback();

    @GetMapping(value = "/business/exception")
    String exception();

    @Component
    @Slf4j
    class HystrixClientFallbackFactory implements FallbackFactory<FallbackFactoryRemote> {
        @SneakyThrows
        @Override
        public FallbackFactoryRemote create(Throwable cause) {
            if (cause instanceof HystrixTimeoutException) {
                log.error("HystrixTimeoutException occurs");
                throw (HystrixTimeoutException)cause;
            }
            log.error("FallbackFactory<FallbackFactoryRemote>", cause);
            return new FallbackFactoryRemote() {
                @Override
                public String fallback() {
                    return "FallbackFactory:fallback";
                }

                @Override
                public String exception() {
                    return "FallbackFactory:exception";
                }
            };
        }
    }
}
