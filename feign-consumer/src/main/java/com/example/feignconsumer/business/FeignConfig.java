package com.example.feignconsumer.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Logger;
import feign.Response;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.slf4j.LoggerFactory;
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

    @Bean
    public ErrorDecoder errorDecoder() {
        return new ErrorDecoder() {

            final org.slf4j.Logger log = LoggerFactory.getLogger(ErrorDecoder.class);

//        @Override
//        public Exception decode(String methodKey, Response response) {
//            ObjectMapper mapper = new ObjectMapper();
//            ApiException resEntity = null;
//            try {
//                resEntity = mapper.readValue(Util.toString(response.body().asReader(StandardCharsets.UTF_8)),
//                ApiException.class);
//            } catch (IOException ex) {
//                log.error(ex.getMessage(), ex);
//            }
//            return resEntity;
//        }

            @Override
            public Exception decode(String methodKey, Response response) {

                String s = response.body().toString();
                ApiExceptionHandler.ErrorResponse r = new ObjectMapper()
                        .convertValue(s, ApiExceptionHandler.ErrorResponse.class);
                log.error("call {} failed, status: {}, errorCode: {}", methodKey, r.status, r.errorCode);
                return new ApiException(r.status, r.errorCode);
            }
        };
    }
}
