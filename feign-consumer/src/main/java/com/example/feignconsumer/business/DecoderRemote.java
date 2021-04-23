package com.example.feignconsumer.business;

import feign.Feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Shanghong Cai
 * @since 2021-04-02
 */
@FeignClient(name="business-instance"
        , contextId = "decoder"
        ,configuration = DecoderRemote.FeignPrototypeConfig.class
)
public interface DecoderRemote {

    @GetMapping( value = "/business/exception")
    String fallback();

    //@Configuration
    class FeignPrototypeConfig {

        @Bean
        @Scope("prototype")
        public Feign.Builder feignBuilder() {
            return Feign.builder();
        }
    }
}
