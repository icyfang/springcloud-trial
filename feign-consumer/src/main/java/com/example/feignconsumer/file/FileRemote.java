package com.example.feignconsumer.file;

import com.example.feignconsumer.business.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Hodur
 * @since 2021-04-23
 */
@FeignClient(name = "business-service"
        ,contextId = "file"
        ,configuration = FeignConfig.class
)
public interface FileRemote extends FileApi{

}
