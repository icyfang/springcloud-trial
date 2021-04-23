/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.13).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.example.feignconsumer.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

public interface FileApi {

    @RequestMapping(value = "/file/download",
            produces = {"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            method = RequestMethod.GET)
    ResponseEntity<Resource> downloadFile();

    @RequestMapping(value = "/file/upload",
            consumes = {"multipart/form-data"},
            method = RequestMethod.POST)
    ResponseEntity<Void> uploadFile(@Valid @RequestPart(value = "file", required = true) MultipartFile file);

}
