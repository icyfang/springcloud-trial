package com.example.feignconsumer.fallback;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.SocketTimeoutException;
import java.net.http.HttpTimeoutException;
import java.util.concurrent.TimeoutException;

/**
 * @author Hodur
 * @date 2021/03/17
 */
@FeignClient(name = "business-service"
        , contextId = "fallback"
        , path = "/example"
        , fallback = FallbackRemote.FallbackFallback.class
)
public interface FallbackRemote {

    @HystrixCommand(
            ignoreExceptions = {HttpTimeoutException.class, HttpTimeoutException.class, TimeoutException.class,
                    HystrixTimeoutException.class, SocketTimeoutException.class
            }
    )
    @GetMapping(value = "/business/fallback")
    String fallback();

    @GetMapping(value = "/business/exception")
    String exception();

    @Component
    class FallbackFallback implements FallbackRemote {

        @Override
        public String fallback() {
            return "fallback";
        }

        @Override
        public String exception() {
            return "exception";
        }
    }

}
