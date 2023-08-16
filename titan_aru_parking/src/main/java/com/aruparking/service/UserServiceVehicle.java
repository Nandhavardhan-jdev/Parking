package com.aruparking.service;

import java.util.List;

import com.aruparking.dto.ParkingVehicleDto;

public interface UserServiceVehicle {

	ParkingVehicleDto add(ParkingVehicleDto parkingVehicleDto);

	ParkingVehicleDto update(ParkingVehicleDto parkingVehicleDto);

	Object delete(long id);

	List<ParkingVehicleDto> savedAc(long id);

	List<ParkingVehicleDto> favAc(long id);

	ParkingVehicleDto defaultAc(long id);

}
