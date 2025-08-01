package com.schedulep1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScheduleP1Application {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleP1Application.class, args);
    }
}
