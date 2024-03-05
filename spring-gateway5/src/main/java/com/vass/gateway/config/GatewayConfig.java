package com.vass.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class GatewayConfig {

	@Bean
	@Profile("localhostRouter-noEureka")
	public RouteLocator configLocalNoEureka(RouteLocatorBuilder builder) {
		return builder.routes().route(r -> r.path("/api/v1/dragonball/*").uri("http://localhost:8082"))
				.route(r -> r.path("/api/v1/game-of-thrones/*").uri("http://localhost:8083")).build();
	}

	@Bean
	@Profile("localhost-eureka")
	public RouteLocator configLocalEureka(RouteLocatorBuilder builder) {
		return builder.routes().route(r -> r.path("/api/v1/dragonball/*").uri("lb://dragon-ball"))
				.route(r -> r.path("/api/v1/game-of-thrones/*").uri("lb://game-of-thrones")).build();
	}
	
	/*Circuit breaking: detecta el mal funcionamiento de un servicio y redirecciona a otro
	 * */
	
	@Bean
	@Profile("localhost-eureka-cb")
	public RouteLocator configLocalEurekaCircuitBraker(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/api/v1/dragonball/*")
						.filters( f -> 
							f.circuitBreaker(
									c -> c.setName("failoverCB")
									.setFallbackUri("forward:/api/v1/db-failover/characters")
									.setRouteId("dbFailover")))
						.uri("lb://dragon-ball"))
				.route(r -> r.path("/api/v1/db-failover/*")
						.uri("lb://dragon-ball-failover"))
				.route(r -> r.path("/api/v1/game-of-thrones/*")
						.uri("lb://game-of-thrones"))
				.build();
	}

}
