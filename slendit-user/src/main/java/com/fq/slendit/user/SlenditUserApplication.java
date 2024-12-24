package com.fq.slendit.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SlenditUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlenditUserApplication.class, args);
		System.out.println("User Application started");
	}

}
