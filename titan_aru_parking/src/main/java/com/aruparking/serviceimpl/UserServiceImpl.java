package com.aruparking.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aruparking.dto.HistoryDto;
import com.aruparking.dto.ParkingSlotsDto;
import com.aruparking.dto.ParkingZonesDto;
import com.aruparking.model.ParkingOrder;
import com.aruparking.model.ParkingSlots;
import com.aruparking.model.ParkingZones;
import com.aruparking.repo.ParkingOrderRepo;
import com.aruparking.repo.ParkingSlotsRepo;
import com.aruparking.repo.ParkingZonesRepo;
import com.aruparking.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	ParkingZonesRepo parkingZonesRepo;
	@Autowired
	ParkingOrderRepo parkingOrderRepo;
	
	@Override
	public ParkingZonesDto add(ParkingZonesDto zone) {
		
		ParkingZones parkingZones=new ParkingZones();
		parkingZones.setZoneName(zone.getZoneName());
		parkingZones.setStatus(zone.getStatus());
		ParkingZones savedZones=parkingZonesRepo.save(parkingZones);

	        return zonedto(savedZones);
	}

	@Override
	public List<ParkingZonesDto> getAll() {
		
		List<ParkingZones> savedZones=parkingZonesRepo.findAll();
		
		List<ParkingZonesDto> parkingZonesDtos=new ArrayList<>();
		for (ParkingZones parkingZones : savedZones) {
			ParkingZonesDto p= zonedto(parkingZones);
			parkingZonesDtos.add(p);
		}
		return parkingZonesDtos;
	}

	@Override
	public ParkingZonesDto get(long id){
		ParkingZones parkingZones= parkingZonesRepo.findById(id);
		if(parkingZones!=null) {
		return zonedto(parkingZones);
		}else {
			throw new RuntimeException("zone id is not present");
		}
	}
	
	@Override 
	public ParkingZonesDto update(ParkingZonesDto parkingZonesDto) {
		ParkingZones parkingZones= parkingZonesRepo.findById(parkingZonesDto.getZoneId());
		if(parkingZones!=null) {
		parkingZones.setZoneName(parkingZonesDto.getZoneName());
		parkingZones.setStatus(parkingZonesDto.getStatus());
		ParkingZones parkingZones3= parkingZonesRepo.save(parkingZones);
		return zonedto(parkingZones3);
		}
		else {
			throw new RuntimeException("id is not present");
		}
	}
	
	public ParkingZonesDto zonedto(ParkingZones parkingZones3) {
		ParkingZonesDto parkingZonesDto=new ParkingZonesDto();
		parkingZonesDto.setZoneId(parkingZones3.getId());
		parkingZonesDto.setZoneName(parkingZones3.getZoneName());
		parkingZonesDto.setStatus(parkingZones3.getStatus());
		parkingZonesDto.setCreatedOn(parkingZones3.getCreatedOn());
		parkingZonesDto.setUpdatedOn(parkingZones3.getUpdatedOn());
		
		return parkingZonesDto;
	}

	public List<HistoryDto> pastHistory(long id){
		ParkingZones parkingZones= parkingZonesRepo.findById(id);
		if(parkingZones!=null) {
			List<ParkingOrder> parkingOrder= parkingOrderRepo.findAllByParkingSlotParkingZonesId(id);
			List<HistoryDto> historyDtos=new ArrayList<>();
			for (ParkingOrder parkingOrder1 : parkingOrder) {
				Date date=new Date();
				int comparsion=date.compareTo(parkingOrder1.getParkingEndTime());
				if(comparsion>0) {
					HistoryDto historyDto= historyDto(parkingOrder1);
					historyDtos.add(historyDto);
				}
			}
			return historyDtos;
		}else {
			throw new RuntimeException("zone id is not present");
		}
	}
	public List<HistoryDto> activeHistory(long id){
		ParkingZones parkingZones= parkingZonesRepo.findById(id);
		if(parkingZones!=null) {
			List<ParkingOrder> parkingOrder= parkingOrderRepo.findAllByParkingSlotParkingZonesId(id);
			List<HistoryDto> historyDtos=new ArrayList<>();
			for (ParkingOrder parkingOrder1 : parkingOrder) {
				Date date=new Date();
				int comparsion=date.compareTo(parkingOrder1.getParkingEndTime());
				if(comparsion<=0) {
					HistoryDto historyDto= historyDto(parkingOrder1);
					historyDtos.add(historyDto);
				}
			}
			return historyDtos;
		}else {
			throw new RuntimeException("zone id is not present");
		}
	}
	public HistoryDto historyDto(ParkingOrder parkingOrder1) {
		HistoryDto historyDto=new HistoryDto();
			historyDto.setUserId(parkingOrder1.getParkingUser().getId());
			historyDto.setZoneId(parkingOrder1.getParkingSlot().getParkingZones().getId());
			historyDto.setSlotId(parkingOrder1.getParkingSlot().getId());
			historyDto.setFeeId(parkingOrder1.getParkingFee().getId());
			historyDto.setOrderId(parkingOrder1.getId());
			historyDto.setVehicleNo(parkingOrder1.getVehicleNo());
			historyDto.setSlotName(parkingOrder1.getParkingSlot().getSlotName());
			historyDto.setAmount(parkingOrder1.getAmount());
			historyDto.setPayee("Aru Parking");
			historyDto.setTransactionId(parkingOrder1.getTransactionId());
			historyDto.setParkingStartTime(parkingOrder1.getParkingStartTime());
			historyDto.setParkingEndTime(parkingOrder1.getParkingEndTime());
			return historyDto;
	}
	
	
}
