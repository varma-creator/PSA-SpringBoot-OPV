package com.psa.opv.newvehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * This Class represents the Springboot App
 * @author Varma
 *
 */
@SpringBootApplication
@EnableCaching
public class OpvApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpvApplication.class, args);
	}

}
