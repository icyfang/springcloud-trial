package com.example.ribbonconsumer.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hodurhongsh
 * @since 2021-04-25
 */
@RestController
public class CacheController {

    @Autowired
    CacheRemote cacheRemote;

    @GetMapping("/hystrix/cache")
    public String cache() {
        cacheRemote.instanceName();
        cacheRemote.instanceName();
        return cacheRemote.instanceName();
    }

    @GetMapping("/hystrix/cacheKey/{id}")
    public String cacheKey(@PathVariable Integer id) {
        cacheRemote.cacheKey(1);
        cacheRemote.cacheKey(1);
        return cacheRemote.cacheKey(1);
    }

    @PostMapping("/hystrix/flushCache")
    public String flushCache() {
        cacheRemote.instanceName();
        cacheRemote.flushInstanceNameCache();
        return cacheRemote.instanceName();
    }
}
