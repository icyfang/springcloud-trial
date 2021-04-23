package com.example.feignconsumer.file;

import com.example.feignconsumer.business.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Shanghong Cai
 * @since 2021-04-23
 */
@FeignClient(name = "business-instance"
        ,contextId = "file"
        ,configuration = FeignConfig.class
)
public interface FileRemote extends FileApi{

}
