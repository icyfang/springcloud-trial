package com.example.feignconsumer.compression;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shanghong Cai
 * @since 2021-04-23
 */
@Controller
public class CompressionController {

    @PostMapping("/compression")
    public void post() {
        List<String> s = new ArrayList<>(2024);
        for (int i = 0; i < 1024; i++) {
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
