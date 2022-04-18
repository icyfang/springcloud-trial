package com.example.businessinstance.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author Hodur
 * @date 2022/02/18
 */

@Component
public class MyHealthChecker implements HealthIndicator {

    private String status = "UP";

    @Override
    public Health health() {
        return new Health.Builder()
//                .withDetail("customized_cnt", 10)
//                .withDetail("customized_status", status)
                .status(status)
                .build();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
