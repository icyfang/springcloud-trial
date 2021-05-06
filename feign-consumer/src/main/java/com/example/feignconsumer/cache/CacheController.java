package com.example.feignconsumer.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hodurhongsh
 * @date 2021-04-25
 */
@RestController
public class CacheController {

    @Autowired
    CacheService cacheService;

    // todo cache not work
    @GetMapping("/hystrix/cache")
    public String cache() {
        cacheService.instanceName();
        cacheService.instanceName();
        return cacheService.instanceName();
    }

    @PostMapping("/hystrix/flushCache")
    public String flushCache() {
        cacheService.instanceName();
        cacheService.removeInstanceNameCache();
        return cacheService.instanceName();
    }
}
