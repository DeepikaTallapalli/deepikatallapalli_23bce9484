package com.example.studentmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.studentmanagement")
@ComponentScan(basePackages = "com.example.studentmanagement")
public class StudentmanagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentmanagementApplication.class, args);
    }
}