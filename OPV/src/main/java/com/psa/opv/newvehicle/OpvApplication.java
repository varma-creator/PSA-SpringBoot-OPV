package com.psa.opv.newvehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * This Class represents the Springboot App
 * @author Varma
 *
 */
@EnableJpaRepositories(basePackages = "com.psa.opv.newvehicle.repository")
@SpringBootApplication
@EnableCaching
public class OpvApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpvApplication.class, args);
	}

}
