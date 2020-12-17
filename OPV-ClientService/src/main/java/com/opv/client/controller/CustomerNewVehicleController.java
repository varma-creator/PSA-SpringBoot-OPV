package com.opv.client.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.opv.client.dto.NewVehicleDTO;


@RestController
@RequestMapping(value="client")
public class CustomerNewVehicleController {

	
	  @Autowired private RestTemplate restTemplate;
	  
	  @HystrixCommand(fallbackMethod = "getAllNewVehicleDetailsfallBack")
	  @GetMapping(value="/get") 
	  public List<NewVehicleDTO> getAllNewVehicleDetails() {
	 //List  list=restTemplate.getForObject("http://OPV-NEWVEHICLE/api/get/allnewvehicles", List.class); 
		  //List  list=restTemplate.getForObject("http://OPV-NEWVEHICLE/api/get/allnewvehicles", List.class);
		ResponseEntity<NewVehicleDTO[]> forEntity = restTemplate.getForEntity("http://OPV-NEWVEHICLE/api/get/allnewvehicles", NewVehicleDTO[].class);
	  return Arrays.asList(forEntity.getBody()); 
	  
	  }
	  public List<NewVehicleDTO> getAllNewVehicleDetailsfallBack() {
	  return Arrays.asList(new NewVehicleDTO("NO Response","","","",0.0,LocalDate.now(),""));
	  }
}
