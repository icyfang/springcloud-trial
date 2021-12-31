package com.example.feignconsumer.business;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Hodur
 * @date 2021/03/17
 */
@SuppressWarnings("SameReturnValue")
@FeignClient(name = "business-service"
        , contextId = "business"
        , configuration = FeignConfig.class
        , fallback = BusinessRemote.BusinessFallback.class
)
public interface BusinessRemote {

    @GetMapping(value = "/business/instanceName")
    String instanceName();

    // todo HystrixObservable
    @GetMapping(value = "/business/instanceName")
    HystrixCommand<String> asyncName();

    @GetMapping(value = "/business/exception")
    String exception();

    @Component
    class BusinessFallback implements BusinessRemote {

        @Override
        public String instanceName() {
            return "default name";
        }

        @Override
        public HystrixCommand<String> asyncName() {
            return null;
        }

        @Override
        public String exception() {
            return "exception";
        }
    }
}
