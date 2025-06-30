package com.example.securitymanagement;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SecurityManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityManagementApplication.class, args);
    }
} 