package com.example.businessinstance.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hodur
 * @since 2021-03-17
 */
@RestController
public class BusinessController implements FileApi {

    @Value("${eureka.instance.appname}")
    private String instanceName;

    @GetMapping(value = "/business/instanceName")
    public String getInstanceName() {
        return instanceName;
    }

    @GetMapping(value = "/business/fallback")
    String fallback() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instanceName;
    }

    // todo HystrixBadRequestException
    @GetMapping(value = "/business/exception")
    String exception() {
        throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "e110011");
    }

    @GetMapping(value = "/compression")
    List<String> get() {
        List<String> s = new ArrayList<>(2024);
        for (int i = 0; i < 1024; i++) {
            s.add("0");
        }
        return s;
    }

    @PostMapping(value = "/compression")
    void post(@RequestBody List<String> s) {
        System.out.println(s.size());
    }

    @Override
    public ResponseEntity<Resource> downloadFile() {

        HttpHeaders headers = new HttpHeaders();
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:files/download.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        headers.set("Content-Disposition", "attachment; filename*=UTF-8''" + UriUtils.encode(file.getName(), "UTF-8"));
        return new ResponseEntity<>(new FileSystemResource(file), headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> uploadFile(@Valid MultipartFile file) {
        System.out.println("getFile");
        return null;
    }
}