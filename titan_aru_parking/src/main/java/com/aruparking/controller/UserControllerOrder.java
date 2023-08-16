package com.aruparking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aruparking.dto.ParkingOrderDto;
import com.aruparking.service.UserServiceOrder;

@RestController
@RequestMapping("/order")
public class UserControllerOrder {

	@Autowired
	UserServiceOrder userServiceOrder;
	
	@PostMapping("/add")
	public Object add(@RequestBody ParkingOrderDto parkingOrderDto) {
		return userServiceOrder.add(parkingOrderDto);
	}
}
