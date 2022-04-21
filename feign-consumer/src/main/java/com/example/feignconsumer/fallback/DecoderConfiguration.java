package com.example.feignconsumer.fallback;

import com.example.feignconsumer.business.ApiException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

/**
 *
 *
 * @author Hodur
 * @date 2022/04/20
 */
@Configuration
public class DecoderConfiguration {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new ErrorDecoder() {

            final org.slf4j.Logger log = LoggerFactory.getLogger(ErrorDecoder.class);

            @Override
            public Exception decode(String methodKey, Response response) {
                ObjectMapper mapper = new ObjectMapper();
                Exception resEntity = null;
                ApiException cause;
                try {
                    String content = Util.toString(new InputStreamReader(new GZIPInputStream(response.body()
                            .asInputStream())));
                    cause = mapper.readValue(content, ApiException.class);
                    resEntity = new HystrixBadRequestException("", cause);
                } catch (IOException ex) {
                    log.error("call {} failed, cause: {}", methodKey, ex.getMessage());
                }
                return resEntity;
            }
        };
    }
}
