package com.example.ribbonconsumer.collapse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * @author Hodur
 * @date 2021/04/25
 */
@RestController
public class CollapseController {

    @Autowired
    private CollapseRemote collapseRemote;

    @GetMapping("/hystrix/collapse/{idList}")
    public List<String> collapse(@PathVariable List<Integer> idList) {
        List<Future<String>> r = new ArrayList<>();
        for (Integer i : idList) {
            r.add(collapseRemote.god(i));
        }
        return r.stream().map(i -> {
            try {
                return i.get(1, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
            return "";
        }).collect(Collectors.toList());

    }
}
