package com.example.feignconsumer.file;

import com.example.feignconsumer.business.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Feign Inheritance Support
 *
 * @author Hodur
 * @date 2021/04/23
 */
@FeignClient(name = "business-service"
        , contextId = "file"
        , path = "/example"
        , configuration = FeignConfig.class
)
public interface FileRemote extends FileApi {

}
