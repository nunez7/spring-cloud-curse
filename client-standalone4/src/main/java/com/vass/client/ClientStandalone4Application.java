package com.vass.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.vass.client.clients.DragonBallCharacterClient;

@SpringBootApplication
@EnableFeignClients
public class ClientStandalone4Application implements ApplicationRunner{
	
	
	private static final Logger log = LoggerFactory.getLogger(ClientStandalone4Application.class);
	
	@Autowired
	private DragonBallCharacterClient dragonballClient;
	
	@Autowired
	private EurekaClient eurekaClient;

	public static void main(String[] args) {
		SpringApplication.run(ClientStandalone4Application.class, args);
	}
	
	public void run(ApplicationArguments args) throws Exception {
		for (int i = 0; i < 10; i++) {
			ResponseEntity<String> responseEntity = dragonballClient.getApplicationName();
			log.info("Status {}", responseEntity.getStatusCode());
			String body = responseEntity.getBody();
			log.info("Body {}", body);
		}
	}
	
	/**
	 * Implementación para clientye de EUREKA
	 */

	/*@Override
	public void run(ApplicationArguments args) throws Exception {
		Application application =  eurekaClient.getApplication("dragon-ball");
		log.info("Application name {}", application.getName());
		
	     List<InstanceInfo> instances = application.getInstances();
	    
	    for (InstanceInfo instanceInfo : instances) {
			log.info("Ip address {}:{}",  instanceInfo.getIPAddr(), instanceInfo.getPort());
		}
	}*/

}
