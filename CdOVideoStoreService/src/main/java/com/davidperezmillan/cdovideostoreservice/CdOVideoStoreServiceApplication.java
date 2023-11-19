package com.davidperezmillan.cdovideostoreservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CdOVideoStoreServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(CdOVideoStoreServiceApplication.class, args);

    }

}
