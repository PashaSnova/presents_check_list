package com.xen.spring.security.java_spring_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class JavaSpringProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSpringProjectApplication.class, args);
    }

}
