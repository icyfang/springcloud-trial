package com.example.feignconsumer.business;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Shanghong Cai
 * @since 2021-03-17
 */
@FeignClient(name = "business-instance"
        ,contextId = "business"
        ,configuration = FeignConfig.class
        ,fallback = BusinessRemote.BusinessFallback.class
)
public interface BusinessRemote  {

    @GetMapping(value = "/business/instanceName")
    public String instanceName();

    // todo HystrixObservable
    @GetMapping(value = "/business/instanceName")
    public HystrixCommand<String> asyncName();

    @GetMapping(value = "/business/exception")
    String exception();

    @Component
    class BusinessFallback implements BusinessRemote{

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
