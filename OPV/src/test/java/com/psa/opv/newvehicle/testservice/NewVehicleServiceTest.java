package com.psa.opv.newvehicle.testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.psa.opv.newvehicle.dto.NewVehicleDTO;
import com.psa.opv.newvehicle.entity.NewVehicle;
import com.psa.opv.newvehicle.repository.NewVehicleRepository;
import com.psa.opv.newvehicle.service.INewVehicleService;
import com.psa.opv.newvehicle.utility.UtilityObjectConversion;

@SpringBootTest
@Disabled
public class NewVehicleServiceTest {

	@MockBean
	private NewVehicleRepository newVehicleRepository;

	@MockBean
	private UtilityObjectConversion utilityObjectConversion;

	@Autowired
	private INewVehicleService iNewVehicleService;

	@Test
	public void testGetNewVehicleId() {
		NewVehicle newVehicle = new NewVehicle();
		newVehicle.setVehicleId("New_Veh_001");
		newVehicle.setVehicleType("DS");
		newVehicle.setVehicleColour("Black");
		newVehicle.setVehicleCost(3000000.0);
		newVehicle.setVehicleManfDate(LocalDate.now());

		NewVehicleDTO newVehicleDTO = new NewVehicleDTO();
		newVehicleDTO.setVehicleColour("Black");
		newVehicleDTO.setVehicleType("DS");
		newVehicleDTO.setVehicleName("Smart_DS");
		newVehicleDTO.setVehicleId("New_Veh_001");
		newVehicleDTO.setVehicleCost(300000.0);
		newVehicleDTO.setVehicleManfDate(LocalDate.now());
		when(newVehicleRepository.findByVehicleId(ArgumentMatchers.anyString())).thenReturn(Optional.of(newVehicle));
		when(utilityObjectConversion.convertNewVehToNewVehDto(ArgumentMatchers.any(NewVehicle.class)))
				.thenReturn(newVehicleDTO);
		Optional<NewVehicleDTO> newVehDto = iNewVehicleService.getNewVehicleID("New_Veh_001");
		assertEquals("DS", newVehDto.get().getVehicleType());

	}
}
