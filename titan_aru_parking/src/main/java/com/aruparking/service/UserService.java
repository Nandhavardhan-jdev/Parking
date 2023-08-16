package com.aruparking.service;

import java.util.List;

import com.aruparking.dto.HistoryDto;
import com.aruparking.dto.ParkingSlotsDto;
import com.aruparking.dto.ParkingZonesDto;
import com.aruparking.model.ParkingSlots;

public interface UserService {
	
	public ParkingZonesDto add(ParkingZonesDto zone);
	public List<ParkingZonesDto> getAll();
	public ParkingZonesDto get(long id);
	public ParkingZonesDto update(ParkingZonesDto parkingZonesDto);
	public List<HistoryDto> pastHistory(long id);
	public List<HistoryDto> activeHistory(long id);
	
}
