package com.example.feignconsumer.file;

import com.example.feignconsumer.business.ApiException;
import com.example.feignconsumer.business.ApiExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Logger;
import feign.Response;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.form.spring.SpringFormEncoder;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormSupportConfig {

    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder();
    }

}
