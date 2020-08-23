package com.example.feignconsumer.upload;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "file",url = "http://localhost:8092",configuration = FeignSupportConfig.class)
public interface UploadService {
    @RequestMapping(value = "/file/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.POST)
    String handleFileUpload(@RequestPart(value = "file") MultipartFile file);
}