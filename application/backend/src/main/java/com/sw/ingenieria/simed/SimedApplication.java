package com.sw.ingenieria.simed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SimedApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(SimedApplication.class, args);
    }

}
