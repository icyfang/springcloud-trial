package com.example.feignconsumer.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @Autowired
    UploadService uploadService;

    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String handleFileUpload(@RequestPart(value = "file") MultipartFile file) {
        return uploadService.handleFileUpload(file);
    }
}