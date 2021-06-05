package com.daniel.cjavazuul.zuulms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class ZuulMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulMsApplication.class, args);
	}

}
