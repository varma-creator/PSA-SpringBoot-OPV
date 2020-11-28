package com.psa.opv.newvehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.psa.opv.newvehicle.repository")
@SpringBootApplication
public class OpvApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpvApplication.class, args);
	}

}
