
package com.psa.opv.newvehicle.testcontroller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psa.opv.newvehicle.controller.NewVehicleController;
import com.psa.opv.newvehicle.dto.NewVehicleDTO;
import com.psa.opv.newvehicle.service.INewVehicleService;

/**
 * This class represents test class for NewVehicleController
 * 
 * @author USER
 *
 */
//@ExtendWith--not required defaulty present
@Disabled
@WebMvcTest(NewVehicleController.class)
public class NewVehicleControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private INewVehicleService iNewVehicleService;

	@Autowired
	private ObjectMapper objectMapper;

	private NewVehicleDTO newVehicleDTO;

	@BeforeEach
	public void testInitNewVehicle() {
		newVehicleDTO = new NewVehicleDTO();
		newVehicleDTO.setVehicleColour("Black");
		newVehicleDTO.setVehicleType("DS");
		newVehicleDTO.setVehicleName("Smart_DS");
		newVehicleDTO.setVehicleId("New_Veh_001");
		newVehicleDTO.setVehicleCost(300000.0);
		newVehicleDTO.setVehicleManfDate(LocalDate.now());
	}

	/**
	 * This test method is used for getvehicleId
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetVehicleById() throws Exception {

		when(iNewVehicleService.getNewVehicleID(ArgumentMatchers.anyString())).thenReturn(Optional.of(newVehicleDTO));
		RequestBuilder requestBuilder = get("/api/get/{vehicleId}", "New_Veh_001").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(jsonPath("$.vehicleType", Matchers.equalTo("DS")));
	}

	@Test
	public void testAddNewVehicle() throws Exception {
		String postvehObj = objectMapper.writeValueAsString(newVehicleDTO);
		when(iNewVehicleService.addNewVehicle(ArgumentMatchers.any(NewVehicleDTO.class)))
				.thenReturn(Optional.of(newVehicleDTO));
		mockMvc.perform(post("/api/add/").contentType(MediaType.APPLICATION_JSON).content(postvehObj)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.vehicleId", Matchers.equalTo("New_Veh_001")));

	}

	@Test
	public void testUpdateNewVehicle() throws Exception {
		NewVehicleDTO newVehicleDTO = new NewVehicleDTO();
		newVehicleDTO.setVehicleColour("Black");
		newVehicleDTO.setVehicleType("AC");
		newVehicleDTO.setVehicleName("Smart_AC");
		newVehicleDTO.setVehicleId("New_Veh_001");
		newVehicleDTO.setVehicleCost(300000.0);
		newVehicleDTO.setVehicleManfDate(LocalDate.now());

		String putvehObj = objectMapper.writeValueAsString(newVehicleDTO);
		when(iNewVehicleService.updateNewVehicleId(ArgumentMatchers.any(NewVehicleDTO.class),
				ArgumentMatchers.anyString())).thenReturn(Optional.of(newVehicleDTO));
		mockMvc.perform(put("/api/update/{vehicleId}", "New_Veh_001").contentType(MediaType.APPLICATION_JSON)
				.content(putvehObj).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.vehicleType", Matchers.equalTo("AC")));
	}

	@Test
	public void testDeleteVehicleId() throws Exception {
		when(iNewVehicleService.deleteByVehicleID(ArgumentMatchers.anyString())).thenReturn(Optional.of(newVehicleDTO));
		mockMvc.perform(delete("/api/delete/{vehicleId}", "New_Veh_001").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.vehicleId", Matchers.equalTo("New_Veh_001")));
	}

}
