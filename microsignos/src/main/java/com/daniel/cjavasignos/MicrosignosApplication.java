package com.daniel.cjavasignos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@EnableDiscoveryClient
@RibbonClient(name = "restTemplate", configuration = {RibbonConfigurator.class})
@EnableCircuitBreaker
@SpringBootApplication
public class MicrosignosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrosignosApplication.class, args);
	}

}
