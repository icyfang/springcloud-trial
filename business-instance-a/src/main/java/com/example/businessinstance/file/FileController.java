package com.example.businessinstance.file;

import com.example.businessinstance.controller.ApiException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 *
 * @author Hodur
 * @date 2022/02/18
 */
@RestController
public class FileController implements FileApi{

    @Override
    public ResponseEntity<Resource> downloadFile() {

        HttpHeaders headers = new HttpHeaders();
        File file;
        try {
            file = ResourceUtils.getFile("classpath:files/download.txt");
        } catch (FileNotFoundException e) {
            throw new ApiException(HttpStatus.NOT_FOUND, "File not found");
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
