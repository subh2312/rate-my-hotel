package com.subhankar.ratemyhotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RateMyHotelApplication {

    public static void main(String[] args) {
        SpringApplication.run(RateMyHotelApplication.class, args);
    }

}
