package com.example.feignconsumer.file;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

public interface FileApi {

    @RequestMapping(value = "/file/download",
            produces = {"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            method = RequestMethod.GET)
    ResponseEntity<Resource> downloadFile();

    @RequestMapping(value = "/file/upload",
            consumes = {"multipart/form-data"},
            method = RequestMethod.POST)
    ResponseEntity<Void> uploadFile(@Valid @RequestPart(value = "file") MultipartFile file);

}
