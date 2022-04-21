package com.example.feignconsumer.business;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 *
 *
 * @author Hodur
 * @date 2022/04/20
 */
public class PrototypeFeignConfig {

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }
}
