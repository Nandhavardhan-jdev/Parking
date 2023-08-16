package com.aruparking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aruparking.model.ParkingSlots;
import com.aruparking.model.ParkingZones;

@Repository
public interface ParkingSlotsRepo extends JpaRepository<ParkingSlots, Long>{

	ParkingSlots findById(long id);
	
	List<ParkingSlots> findAllByparkingZonesId(long id);

	ParkingSlots findBySlotName(String slotName);

	ParkingSlots findBySlotNameAndParkingZonesId(String slotName, long l);


	
}
