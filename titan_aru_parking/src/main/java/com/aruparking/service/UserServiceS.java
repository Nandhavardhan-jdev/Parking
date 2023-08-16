package com.aruparking.service;

import java.util.List;

import com.aruparking.dto.HistoryDto;
import com.aruparking.dto.ParkingSlotsDto;

public interface UserServiceS {

	public Object addSlot(ParkingSlotsDto parkingSlotsdto);

	public List<ParkingSlotsDto> getall();

	public Object get(long id);

	public Object update(ParkingSlotsDto parkingSlotsDto);

	public List<Object> getAllByZoneId(long id);

	public List<HistoryDto> pastHistory(long id);

	public List<HistoryDto> activeHistory(long id);
}
