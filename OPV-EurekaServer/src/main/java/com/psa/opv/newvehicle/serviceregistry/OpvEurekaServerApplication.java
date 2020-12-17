package com.psa.opv.newvehicle.serviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class OpvEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpvEurekaServerApplication.class, args);
	}

}
