package com.daniel.cjavasignos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;

@Configuration
public class RibbonConfigurator {
	
	@Bean
	public IRule ribbonRule() {
		 return new RoundRobinRule();
	}
}