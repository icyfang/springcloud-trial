package com.example.feignconsumer.cache;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Hodur
 * @date 2021/04/25
 */
@FeignClient(name = "business-service"
        , contextId = "cache"
        , path = "/example"
)
public interface CacheRemote {

    @GetMapping(value = "/business/instanceName")
    String cache();

}
