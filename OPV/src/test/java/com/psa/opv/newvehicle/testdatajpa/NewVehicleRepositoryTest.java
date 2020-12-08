package com.psa.opv.newvehicle.testdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.psa.opv.newvehicle.entity.NewVehicle;
import com.psa.opv.newvehicle.repository.NewVehicleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class NewVehicleRepositoryTest {

	@Autowired
	private NewVehicleRepository newVehicleRepository;

	@Test
	@Disabled
	public void testSaveNewVehicleData() {
		NewVehicle newVehicle = new NewVehicle();
		// newVehicle.setVehicleId("New_Veh_009");
		newVehicle.setVehicleType("PS");
		newVehicle.setVehicleColour("Black");
		newVehicle.setVehicleCost(3000000.0);
		newVehicle.setVehicleUniqueNum("PS_To_B");
		newVehicle.setVehicleName("psaCAR");
		newVehicle.setVehicleManfDate(LocalDate.now());

		NewVehicle newVehicleSaveData = newVehicleRepository.save(newVehicle);
		// assertEquals(newVehicleSaveData.getVehicleId(),
		// newVehicleRepository.findByVehicleId("New_Veh_005").get().getVehicleId());
		assertNotNull(newVehicleSaveData);

	}

	@Test
	public void testGetNewVehicleId() {
		Optional<NewVehicle> findByVehicleId = newVehicleRepository.findByVehicleId("New_Veh_005");
		assertEquals(findByVehicleId.get().getVehicleId(), "New_Veh_005");
	}

	@Test
	public void testUpdateNewVehicle() {
		NewVehicle newVehicle = new NewVehicle();
		// newVehicle.setVehicleId("New_Veh_009");
		newVehicle.setVehicleColour("WHITE");
		newVehicle.setVehicleCost(41000.0);
		newVehicle.setVehicleManfDate(LocalDate.now());
		Optional<NewVehicle> findByVehicleId = newVehicleRepository.findByVehicleId("New_Veh_005");
		NewVehicle newVehicle2 = findByVehicleId.get();
		newVehicle2.setVehicleColour(newVehicle.getVehicleColour());
		newVehicle2.setVehicleCost(newVehicle.getVehicleCost());

		NewVehicle saved = newVehicleRepository.save(newVehicle2);
		assertEquals(saved.getVehicleColour(), "WHITE");
	}

	@Test
	public void testDeleteVehicleId() {
		List<NewVehicle> removeByVehicleId = newVehicleRepository.removeByVehicleId("New_Veh_004");
		assertEquals(removeByVehicleId.get(0).getVehicleId(), "New_Veh_004");

	}

}
