package com.aruparking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aruparking.dto.HistoryDto;
import com.aruparking.dto.ParkingUserDto;
import com.aruparking.model.ParkingUser;
import com.aruparking.service.UserServiceUser;

@RestController
@RequestMapping("/user")
public class UserControllerUser {

	@Autowired
	UserServiceUser userServiceUser;
	
	@PostMapping("/add")
	public ParkingUserDto add(@RequestBody ParkingUser parkingUser) {
		return userServiceUser.add(parkingUser);
	}
	@GetMapping("pasthistory/{id}")
	public List<HistoryDto> getPastHistory(@PathVariable long id) {
		return userServiceUser.getPastHistory(id);
	}
	@GetMapping("activehistory/{id}")
	public List<HistoryDto> getActiveHistory(@PathVariable long id) {
		return userServiceUser.getActivetHistory(id);
	}
}
