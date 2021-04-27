package com.example.feignconsumer.cache;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Hodur
 * @since 2021-04-25
 */
@FeignClient(name = "business-service"
        , contextId = "cache"
)
public interface CacheRemote {

    @GetMapping(value = "/business/instanceName")
    public String cache();

}
