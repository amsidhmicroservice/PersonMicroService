package com.amsidh.mvc.personws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableEurekaClient
@EnableRetry
public class PersonWsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonWsApplication.class, args);
    }

}
