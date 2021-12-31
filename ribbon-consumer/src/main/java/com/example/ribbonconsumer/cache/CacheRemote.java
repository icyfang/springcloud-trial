package com.example.ribbonconsumer.cache;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Hodur
 * @date 2021/04/25
 */
@Service
@Slf4j
public class CacheRemote {

    @Value("${service-url.business-service}")
    String serviceName;

    @Autowired
    RestTemplate restTemplate;

    @CacheResult
    @HystrixCommand(commandKey = "instanceName")
    public String cacheKey(@CacheKey Integer id) {
        log.info("request /business/god/{id}");
        ResponseEntity<String> forEntity = restTemplate
                .getForEntity(serviceName + "/business/god/{id}", String.class, id);
        return forEntity.getBody();
    }

    @CacheResult(cacheKeyMethod = "instanceNameKey")
    @HystrixCommand(commandKey = "instanceName")
    public String instanceName() {
        log.info("request /business/instanceName");
        ResponseEntity<String> forEntity = restTemplate
                .getForEntity(serviceName + "/business/instanceName", String.class);
        return forEntity.getBody();
    }

    @CacheResult(cacheKeyMethod = "instanceNameKey")
    @CacheRemove(cacheKeyMethod = "instanceNameKey", commandKey = "flushInstanceNameCache")
    @HystrixCommand(commandKey = "flushInstanceNameCache")
    public String flushInstanceNameCache() {
        log.info("flush cache for /business/instanceName");
        ResponseEntity<String> forEntity = restTemplate
                .getForEntity(serviceName + "/business/instanceName", String.class);
        return forEntity.getBody();
    }

    public String instanceNameKey() {
        return "instanceName";
    }
}
