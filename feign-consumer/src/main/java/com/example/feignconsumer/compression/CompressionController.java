package com.example.feignconsumer.compression;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hodur
 * @date 2021-04-23
 */
@RestController
public class CompressionController {

    @PostMapping("/compression")
    public void post() {
        List<String> s = new ArrayList<>(2048);
        for (int i = 0; i < 2048; i++) {
            s.add("0");
        }
        remote.post(s);
    }

    @GetMapping("/compression")
    public List<String> get(){
        return remote.get();
    }

    @Autowired
    private CompressionRemote remote;
}
