package com.aruparking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aruparking.dto.ParkingZonesDto;
import com.aruparking.model.ParkingSlots;
import com.aruparking.model.ParkingZones;

@Repository
public interface ParkingZonesRepo extends JpaRepository<ParkingZones, Long>{

	ParkingZones findById(long id);

}
