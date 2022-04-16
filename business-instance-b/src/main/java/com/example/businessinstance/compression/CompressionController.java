package com.example.businessinstance.compression;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Hodur
 * @date 2022/02/18
 */
@RestController
public class CompressionController {

    @GetMapping(value = "/compression")
    List<String> get() {
        int capacity = 2048;
        List<String> s = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            s.add("0");
        }
        return s;
    }

    @PostMapping(value = "/compression")
    void post(@RequestBody List<String> s) {
        System.out.println(s.size());
    }
}