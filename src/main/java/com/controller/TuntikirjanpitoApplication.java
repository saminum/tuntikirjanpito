package com.controller;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@ComponentScan("com.dao")
@ComponentScan(basePackageClasses = TuntikirjausController.class)
@EnableAutoConfiguration
public class TuntikirjanpitoApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TuntikirjanpitoApplication.class);
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(TuntikirjanpitoApplication.class, args);
	}
}
