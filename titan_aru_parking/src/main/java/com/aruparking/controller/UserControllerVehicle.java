package com.aruparking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aruparking.dto.ParkingVehicleDto;
import com.aruparking.service.UserServiceVehicle;

@RestController
@RequestMapping("/vehicle")
public class UserControllerVehicle {

	@Autowired
	UserServiceVehicle userServiceVehicle;
	
	@PostMapping("/add")
	public ParkingVehicleDto add(@RequestBody ParkingVehicleDto parkingVehicleDto) {
		return userServiceVehicle.add(parkingVehicleDto);
	}
	
	@PutMapping("/update")
	public ParkingVehicleDto update(@RequestBody ParkingVehicleDto parkingVehicleDto) {
		return userServiceVehicle.update(parkingVehicleDto);
	}
	
	@DeleteMapping("/delete/{id}")
	public Object delete(@PathVariable long id) {
		return userServiceVehicle.delete(id);
	}
	
	@GetMapping("/savedac/{id}")
	public List<ParkingVehicleDto> savedAc(@PathVariable long id) {
		return userServiceVehicle.savedAc(id);
	}
	
	@GetMapping("/favac/{id}")
	public List<ParkingVehicleDto> favAc(@PathVariable long id){
		return userServiceVehicle.favAc(id);
	}
	
	@GetMapping("/defaultac/{id}")
	public ParkingVehicleDto defaultAc(@PathVariable long id) {
		return userServiceVehicle.defaultAc(id);
	}
}
