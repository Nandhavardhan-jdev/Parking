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
import com.aruparking.dto.ParkingSlotsDto;
import com.aruparking.service.UserServiceS;

@RestController
@RequestMapping("/slots")
public class UserControllerSlots {

	@Autowired
	UserServiceS userServiceS;

	@PostMapping("/add")
	public Object add(@RequestBody ParkingSlotsDto parkingSlotsdto) {
		return userServiceS.addSlot(parkingSlotsdto);
	}
	
	@GetMapping("/getall")
	public List<ParkingSlotsDto> getall() {
		return userServiceS.getall();
	}
	
	@GetMapping("/get/{id}")
	public Object get(@PathVariable long id) {
		return userServiceS.get(id);
	}
	
	@PutMapping("/update")
	public Object update(@RequestBody ParkingSlotsDto parkingSlotsDto) {
		return userServiceS.update(parkingSlotsDto);
	}
	
	@GetMapping("/getallbyzoneid/{id}")
	public List<Object> getAllByZoneId(@PathVariable long id) {
		return userServiceS.getAllByZoneId(id);
	}
	@GetMapping("/pasthistory/{id}")
	public List<HistoryDto> pastHistory(@PathVariable long id){
		return userServiceS.pastHistory(id);
	}
	@GetMapping("/activehistory/{id}")
	public List<HistoryDto> activeHistory(@PathVariable long id){
		return userServiceS.activeHistory(id);
	}
}
