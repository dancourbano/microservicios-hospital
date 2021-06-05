package com.daniel.cjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicroerurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroerurekaApplication.class, args);
	}

}
