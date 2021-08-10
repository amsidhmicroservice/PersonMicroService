package com.amsidh.mvc.addressws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AddressWsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddressWsApplication.class, args);
    }

}
