package com.aruparking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aruparking.dto.ParkingFeeDto;
import com.aruparking.service.UserServiceFee;
import com.aruparking.serviceimpl.UserServiceFeeImpl;

@RestController
@RequestMapping("/fee")
public class UserControllerFee {

	@Autowired
	UserServiceFee userServiceFee;
	
	@PostMapping("/add")
	public ParkingFeeDto add(@RequestBody ParkingFeeDto parkingFeeDto) {
		return userServiceFee.add(parkingFeeDto);
	}
	@GetMapping("/getall")
	public Object getAll() {
		return userServiceFee.getAll();
	}
	
}
