package com.example.feignconsumer.fallback;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 *
 * @author Hodur
 * @date 2022/04/25
 */
@Service
public class FallbackService {

    @Autowired
    private FallbackRemote fallbackRemote;

    // ignoreExceptions not work
    @HystrixCommand(fallbackMethod = "doFallback", ignoreExceptions = {NullPointerException.class})
    public String fallback() {
        if (true) {
            throw new NullPointerException();
        }
        return fallbackRemote.fallback();
    }

    public String doFallback(){
        return "service/fallback";
    }
    
}
