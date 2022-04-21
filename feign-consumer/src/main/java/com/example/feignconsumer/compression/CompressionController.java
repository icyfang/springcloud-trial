package com.example.feignconsumer.compression;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * @author Hodur
 * @date 2021/04/23
 */
@RestController
public class CompressionController {

    @PostMapping("/compression")
    public void post() {
        int capacity = 2048;
        List<String> s = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            s.add("0");
        }
        remote.post(s);
    }

    @GetMapping("/compression")
    public List<String> get() throws IOException {
        ResponseEntity<byte[]> responseEntity = remote.get();
        String content =
                Util.toString(new InputStreamReader(new GZIPInputStream(new ByteArrayInputStream(responseEntity.getBody()))));
        return new ObjectMapper().readValue(content, new TypeReference<>() {
        });

    }

    @Autowired
    private CompressionRemote remote;
}
