package com.aruparking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aruparking.model.ParkingVehicle;

@Repository
public interface ParkingVehicleRepo extends JpaRepository<ParkingVehicle, Long>{

	ParkingVehicle findByParkingUserIdAndVehicleNo(long userId, String vehicleNo);
	
	ParkingVehicle findById(long id);

	ParkingVehicle findByDefaultVehicleAndParkingUserId(boolean defaultVehicle, long userId);

	List<ParkingVehicle> findAllByParkingUserId(long id);

}
