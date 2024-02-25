package com.tananushka.javaadvanced.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.tananushka.javaadvanced"})
@EnableJpaRepositories(basePackages = "com.tananushka.javaadvanced.serviceimpl.repository")
@EntityScan(basePackages = "com.tananushka.javaadvanced.serviceimpl.entity")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}