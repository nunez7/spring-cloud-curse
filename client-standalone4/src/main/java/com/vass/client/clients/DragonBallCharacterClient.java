package com.vass.client.clients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.vass.client.config.LoadBalancerConfiguration;

@FeignClient(name = "dragon-ball")
@LoadBalancerClient(name = "dragon-ball", configuration = LoadBalancerConfiguration.class)
public interface DragonBallCharacterClient {
	
	@GetMapping("/application-name")
	public ResponseEntity<String> getApplicationName();

}
