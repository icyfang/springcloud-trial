package com.example.feignconsumer.cache;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hodur
 * @date 2021/04/25
 */
@Service
@Slf4j
public class CacheService {

    @Autowired
    private CacheRemote cacheRemote;

    @CacheResult(cacheKeyMethod = "instanceNameKey")
    @HystrixCommand(commandKey = "instanceName"
            , commandProperties = {
            @HystrixProperty(name = "requestCache.enabled", value = "true")
    })
    public String instanceName() {
        log.info("request /business/instanceName");
        return cacheRemote.cache();
    }

    @CacheRemove(cacheKeyMethod = "instanceNameKey", commandKey = "instanceName")
    @HystrixCommand
    public void removeInstanceNameCache() {
        log.info("remove cache for /business/instanceName");
    }

    public String instanceNameKey() {
        return "instanceName";
    }
}
