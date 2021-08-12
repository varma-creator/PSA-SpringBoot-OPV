package com.psa.opv.customer.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.psa.opv.customer.dto.NewVehicleDTO;

@RestController
@RequestMapping(value = "/customer")
public class CustomerServiceController {

	RestTemplate restTemplate= new RestTemplate();
	@GetMapping
	public List<NewVehicleDTO> getNewVehicleDetails()
	{
		
		return null;
	}
}
