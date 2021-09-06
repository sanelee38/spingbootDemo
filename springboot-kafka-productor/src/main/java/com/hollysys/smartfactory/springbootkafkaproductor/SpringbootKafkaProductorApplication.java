package com.hollysys.smartfactory.springbootkafkaproductor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.hollysys.smartfactory.springbootkafkaproductor.controller")
@ComponentScan("com.hollysys.smartfactory.springbootkafkaproductor.config")
public class SpringbootKafkaProductorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootKafkaProductorApplication.class, args);
    }

}
