package com.psa.opv.customer;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.psa.opv.customer.dto.NewVehicleDTO;

@SpringBootApplication
public class CustomerRestServiceConsumesNewVehicleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerRestServiceConsumesNewVehicleApplication.class, args);
		// getNewVehiclById();
		// addNewVehicle();
		// updateNewVehicle();
		//deleteNewVehicle();
	}

	public static RestTemplate restTemplate = new RestTemplate();
	public static final String url = "http://localhost:8080/api/";

	public static void getNewVehiclById() {
//difference between application_json ==it retruns mediatype and application_json_value===it returns string
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(httpHeaders);
		Map<String, String> pathvaribleMap = new HashMap<String, String>();
		pathvaribleMap.put("vehicleId", "New_Veh_012");
		ResponseEntity<NewVehicleDTO> exchange = restTemplate.exchange(url + "{vehicleId}", HttpMethod.GET, httpEntity,
				NewVehicleDTO.class, pathvaribleMap);
		NewVehicleDTO body = exchange.getBody();
		System.out.println("NewVehicleDetails:" + body.toString());
		HttpHeaders headers = exchange.getHeaders();
		System.out.println("NewVehicleServers Headers:" + headers);
		HttpStatus statusCode = exchange.getStatusCode();
		System.out.println("Status code:" + statusCode);
	}

	public static void addNewVehicle() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		NewVehicleDTO newVehicleDTO = new NewVehicleDTO();
		newVehicleDTO.setVehicleCost(30000.0d);
		newVehicleDTO.setVehicleColour("RED");
		newVehicleDTO.setVehicleManfDate(LocalDate.now());
		newVehicleDTO.setVehicleType("Citreion");
		newVehicleDTO.setVehicleName("VARMACAR");
		// here defaulty converting object into json
		HttpEntity<NewVehicleDTO> htpEntity = new HttpEntity<NewVehicleDTO>(newVehicleDTO, httpHeaders);

		ResponseEntity<NewVehicleDTO> newVehData = restTemplate.exchange(url + "add", HttpMethod.POST, htpEntity,
				NewVehicleDTO.class);

		NewVehicleDTO body = newVehData.getBody();
		System.out.println("Post data======" + body);
		HttpStatus statusCode = newVehData.getStatusCode();
		System.out.println("Statuscode" + statusCode);
	}

	public static void updateNewVehicle() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		NewVehicleDTO newVehicleDTO = new NewVehicleDTO();
		newVehicleDTO.setVehicleCost(400000.0d);
		newVehicleDTO.setVehicleColour("RED");
		newVehicleDTO.setVehicleManfDate(LocalDate.now());
		newVehicleDTO.setVehicleType("AC");
		newVehicleDTO.setVehicleName("PSAVarmaCAR");
		HttpEntity<NewVehicleDTO> httpEntity = new HttpEntity<NewVehicleDTO>(newVehicleDTO, httpHeaders);
		Map<String, String> pathMap = new LinkedHashMap<>();
		pathMap.put("vehicleId", "New_Veh_014");
		ResponseEntity<NewVehicleDTO> exchange = restTemplate.exchange(url + "update" + "/" + "{vehicleId}",
				HttpMethod.PUT, httpEntity, NewVehicleDTO.class, pathMap);
		System.out.println("body======" + exchange.getBody());
		System.out.println("status code=====" + exchange.getStatusCode());
	}

	/*
	 * passing query params in request
	 * String uri = http://my-rest-url.org/rest/account/{account};
	 * 
	 * Map<String, String> uriParam = new HashMap<>(); uriParam.put("account",
	 * "my_account");
	 * 
	 * UriComponents builder = UriComponentsBuilder.fromHttpUrl(uri)
	 * .queryParam("pageSize","2") .queryParam("page","0")
	 * .queryParam("name","my_name").build();
	 * 
	 * HttpEntity<String> requestEntity = new HttpEntity<>(null, getHeaders());
	 * 
	 * ResponseEntity<String> strResponse =
	 * restTemplate.exchange(builder.toUriString(),HttpMethod.GET, requestEntity,
	 * String.class,uriParam);
	 */
	public static void deleteNewVehicle() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<HttpHeaders> httpEntity = new HttpEntity<HttpHeaders>(httpHeaders);
		Map<String, String> pathMap = new LinkedHashMap<>();
		pathMap.put("vehicleId", "New_Veh_014");
		ResponseEntity<NewVehicleDTO> exchange = restTemplate.exchange(url + "delete" + "/" + "{vehicleId}",
				HttpMethod.DELETE, httpEntity, NewVehicleDTO.class, pathMap);
		System.out.println("bod===delete==" + exchange.getBody());
		System.out.println(exchange.getStatusCode());
	}
}
