package com.example.businessinstance.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author Hodur
 * @date 2022/02/18
 */
@RestController
@RequestMapping
public class HealthController {

    @Autowired
    MyHealthChecker myHealthChecker;

//    @Autowired
//    MyHealthCheckHandler healthCheckHandler;

    @PutMapping("/status")
    public String updateStatus(@RequestParam("status") String status) {
        myHealthChecker.setStatus(status);
//        healthCheckHandler.setStatus(status);

        return status;
    }

}
