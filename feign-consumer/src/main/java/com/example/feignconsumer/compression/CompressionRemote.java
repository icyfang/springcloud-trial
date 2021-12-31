package com.example.feignconsumer.compression;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Hodur
 * @date 2021/04/23
 */
@FeignClient(name = "business-service"
        , contextId = "compression"
)
public interface CompressionRemote {

    @PostMapping("/compression")
    void post(@RequestBody List<String> s);

    @GetMapping("/compression")
    List<String> get();
}
