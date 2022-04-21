package com.example.feignconsumer.business;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(1000, 3000, 3);
    }

//    @Bean
//    public WebMvcRegistrations feignWebRegistrations() {
//        return new WebMvcRegistrationsAdapter() {
//            @Override
//            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
//                return new RequestMappingHandlerMapping() {
//                    @Override
//                    protected boolean isHandler(Class<?> beanType) {
//                        return super.isHandler(beanType) &&
//                                !AnnotatedElementUtils.hasAnnotation(beanType, FeignClient.class);
//                    }
//                };
//            }
//        };
//    }


}
