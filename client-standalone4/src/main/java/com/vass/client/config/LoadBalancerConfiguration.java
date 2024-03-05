package com.vass.client.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalancerConfiguration {
	private static final Logger log = LoggerFactory.getLogger(LoadBalancerConfiguration.class);
	
	@Bean
	public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(ConfigurableApplicationContext context) {
		log.info("Configuraring Load Balancer to prefer same instance");
		return ServiceInstanceListSupplier.builder()
				.withBlockingDiscoveryClient()
				.withSameInstancePreference()
				.build(context);
	}
}
