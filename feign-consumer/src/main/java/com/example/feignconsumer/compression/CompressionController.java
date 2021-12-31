package com.example.feignconsumer.compression;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hodur
 * @date 2021/04/23
 */
@Controller
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
    public List<String> get() {
        return remote.get();
    }

    @Autowired
    private CompressionRemote remote;
}
