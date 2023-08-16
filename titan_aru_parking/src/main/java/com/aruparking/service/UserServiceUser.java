package com.aruparking.service;

import java.util.List;

import com.aruparking.dto.HistoryDto;
import com.aruparking.dto.ParkingUserDto;
import com.aruparking.model.ParkingUser;

public interface UserServiceUser {

	ParkingUserDto add(ParkingUser parkingUser);

	List<HistoryDto> getPastHistory(long id);

	List<HistoryDto> getActivetHistory(long id);

	
}
