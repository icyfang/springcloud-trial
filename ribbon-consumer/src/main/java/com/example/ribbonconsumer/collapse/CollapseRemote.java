package com.example.ribbonconsumer.collapse;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author Hodur
 * @date 2021/04/25
 */
@Service
@Slf4j
public class CollapseRemote {

    @Value("${service-url.business-service}")
    String serviceName;

    @Autowired
    RestTemplate restTemplate;

    @HystrixCollapser(batchMethod = "listGod"
            , collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "100")
    })
    public Future<String> god(Integer id) {
        log.info("request /business/god/{id} with id: {}", id);
        ResponseEntity<String> forEntity = restTemplate
                .getForEntity(serviceName + "/business/god/{id}", String.class, id);
        return new AsyncResult<>(forEntity.getBody());
    }

    @HystrixCommand
    public List<String> listGod(List<Integer> ids) {
        String collect = StringUtils.join(ids, ",");
        log.info("request /business/godList/{id} with id: {}", collect);
        ResponseEntity<List> forEntity = restTemplate
                .getForEntity(serviceName + "/business/godList/{id}", List.class, collect);
        return ((List<String>) forEntity.getBody());
    }
}
