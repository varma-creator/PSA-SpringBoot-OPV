package com.psa.opv.newvehicle.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OpvGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpvGatewayApplication.class, args);
	}

}
