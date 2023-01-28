package com.company.readnovel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ReadnovelApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReadnovelApplication.class, args);
    }
}
