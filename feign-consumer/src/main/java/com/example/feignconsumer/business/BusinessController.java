package com.example.feignconsumer.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @author Hodur
 * @date 2021-03-17
 */
@RestController
public class BusinessController {

    @Autowired
    private BusinessRemote remote;

    @Autowired
    private DecoderRemote decoderRemote;

    @GetMapping(value = "/business/instanceName")
    public String getInstanceName() {
        return remote.instanceName();
    }

    @GetMapping(value = "/business/async")
    public String getAsync() throws ExecutionException, InterruptedException {
        return remote.asyncName().queue().get();
    }

    @GetMapping("/business/decodeException")
    public String decodeException() {
        return decoderRemote.fallback();
    }

    // todo
    @GetMapping(value = "/business/exception")
    String exception() {
        try {
            return remote.exception();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
