package com.example.businessinstance.controller;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hodur
 * @date 2021/03/17
 */
@RestController
@RequestMapping("/business")
public class BusinessController {

    final static List<String> GOD_LIST = Arrays.asList("Odin", "Frigg", "Thor", "Siv", "Freyr", "Loki", "Tyr",
            "Freyja", "Heimdall", "Baldur", "Hoder", "Budle");

    @Value("${eureka.instance.appname}")
    private String instanceName;

    @GetMapping(value = "/instanceName")
    public String getInstanceName() {
        return instanceName;
    }

    @GetMapping(value = "/god/{id}")
    public String getGod(@PathVariable Integer id) {
        return GOD_LIST.get(id);
    }

    @GetMapping(value = "/godList/{idList}")
    public List<String> listGod(@PathVariable List<Integer> idList) {
        return idList.stream().map(GOD_LIST::get).collect(Collectors.toList());
    }

    @GetMapping(value = "/fallback")
    String fallback() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instanceName;
    }

    @GetMapping(value = "/exception")
    String exception() {
        throw new HystrixBadRequestException("", new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "e110011"));
//        throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "e110011");
    }

}
