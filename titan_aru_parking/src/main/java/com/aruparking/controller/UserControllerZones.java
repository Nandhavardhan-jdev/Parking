package com.aruparking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aruparking.dto.HistoryDto;
import com.aruparking.dto.ParkingZonesDto;
import com.aruparking.model.ParkingSlots;
import com.aruparking.service.UserService;

@RestController
@RequestMapping("/zones")
public class UserControllerZones {

	@Autowired
	UserService userService;
	
	@PostMapping("/add")
	public ParkingZonesDto add(@RequestBody ParkingZonesDto zone) {
		return userService.add(zone);
	}
	
	@GetMapping("/getall")
	public List<ParkingZonesDto> getAll() {
		return userService.getAll();
	}
	
	@GetMapping("/get/{id}")
	public ParkingZonesDto get(@PathVariable long id) {
		return userService.get(id);
	}
	
	@PutMapping("/update")
	public ParkingZonesDto update(@RequestBody ParkingZonesDto parkingZonesDto) {
		return userService.update(parkingZonesDto);
	}
	
	@GetMapping("/pasthistory/{id}")
	public List<HistoryDto> pastHistory(@PathVariable long id){
		return userService.pastHistory(id);
	}
	@GetMapping("/activehistory/{id}")
	public List<HistoryDto> activeHistory(@PathVariable long id){
		return userService.activeHistory(id);
	}
}
