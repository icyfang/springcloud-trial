package com.example.feignconsumer.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * @author Hodur
 * @date 2021-04-23
 */
@Controller
public class FileController implements FileApi{

    @Autowired
    private FileRemote remote;

    @Override
    public ResponseEntity<Resource> downloadFile() {
        return remote.downloadFile();
    }

    @Override
    public ResponseEntity<Void> uploadFile(@Valid MultipartFile file) {
        return remote.uploadFile(file);
    }
}
