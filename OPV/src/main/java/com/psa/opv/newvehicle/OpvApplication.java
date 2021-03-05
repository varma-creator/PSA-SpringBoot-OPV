package com.psa.opv.newvehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * This Class represents the Springboot App
 * @author Varma yadav2
 *
 */
@SpringBootApplication
@EnableCaching
@EnableEurekaClient
public class OpvApplication {

	public static void main(String[] args) {
		//conflictremote
		SpringApplication.run(OpvApplication.class, args);
		//git local conflicts
		//git conflicts
<<<<<<< HEAD
		//local changes
=======
		//added some changes
>>>>>>> branch 'main' of https://github.com/varma-creator/PSA-SpringBoot-OPV.git
	}

}
