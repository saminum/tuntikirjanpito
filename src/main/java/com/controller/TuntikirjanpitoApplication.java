package com.controller;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.dao")
@ComponentScan(basePackageClasses = TuntikirjausController.class)
public class TuntikirjanpitoApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(TuntikirjanpitoApplication.class, args);
    }

}
