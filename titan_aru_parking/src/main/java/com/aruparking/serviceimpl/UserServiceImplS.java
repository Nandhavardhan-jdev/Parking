package com.aruparking.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aruparking.dto.HistoryDto;
import com.aruparking.dto.ParkingSlotsDto;
import com.aruparking.model.ParkingOrder;
import com.aruparking.model.ParkingSlots;
import com.aruparking.model.ParkingZones;
import com.aruparking.repo.ParkingOrderRepo;
import com.aruparking.repo.ParkingSlotsRepo;
import com.aruparking.repo.ParkingZonesRepo;
import com.aruparking.service.UserServiceS;

@Service
public class UserServiceImplS implements UserServiceS{
	
	@Autowired
	ParkingSlotsRepo parkingSlotsRepo;
	@Autowired
	ParkingZonesRepo parkingZonesRepo;
	@Autowired
	ParkingOrderRepo parkingOrderRepo;
	
	@Override
	public Object addSlot(ParkingSlotsDto parkingSlotsdto) {
		ParkingSlots p=new ParkingSlots();
		ParkingZones parkingZones=parkingZonesRepo.findById(parkingSlotsdto.getZoneId());
		if(parkingZones!=null) 
		{
		ParkingSlots parkingslots=parkingSlotsRepo.findBySlotNameAndParkingZonesId(parkingSlotsdto.getSlotName(), parkingSlotsdto.getZoneId());
		if(parkingslots==null) 
		{
		p.setSlotName(parkingSlotsdto.getSlotName());
		p.setStatus(1);
		p.setParkingZones(parkingZones);
		p=parkingSlotsRepo.save(p);
		ParkingSlotsDto parkingSlotsDto2= slotdto(p);
		return parkingSlotsDto2;
		}
		else {
//			throw new RuntimeException("parking slot already exists");
			Map<String, String> map=new HashMap<>();
			map.put("error ", "parkingslot already exists");
			return map;
		}
		}
		else {
//			throw new RuntimeException("parkingzone is not present");
			List<String> list=new ArrayList<>();
			list.add("parkingzone is not present");
			return list;
		} 
	
	} 
	
	
	public List<ParkingSlotsDto> getall() {
		List<ParkingSlots> parkingSlots= parkingSlotsRepo.findAll();
		List<ParkingSlotsDto> parkingSlotsdto=new ArrayList<>();
		for (ParkingSlots parkingSlots3 : parkingSlots) {
			ParkingSlotsDto parkingSlots4=slotdto(parkingSlots3);
			parkingSlotsdto.add(parkingSlots4);
		}
		return parkingSlotsdto;
	}
	
	public Object get(long id) {
		ParkingSlots parkingSlots= parkingSlotsRepo.findById(id);
		if(parkingSlots!=null) {
			return slotdto(parkingSlots);
		}
		else {
//			throw new RuntimeException("id is not present");
			List<String> list=new ArrayList<>();
			list.add("id is not present");
			return list;
		}
	}
	
	public Object update(ParkingSlotsDto parkingSlotsDto) {
		ParkingSlots parkingSlots= parkingSlotsRepo.findById(parkingSlotsDto.getSlotId());
		if(parkingSlots !=null) {
			ParkingSlots parkingSlots2= parkingSlotsRepo.findBySlotNameAndParkingZonesId( parkingSlotsDto.getSlotName(),parkingSlotsDto.getZoneId());
			if(parkingSlots2==null) {
				parkingSlots.setSlotName(parkingSlotsDto.getSlotName());
				parkingSlots.setStatus(parkingSlotsDto.getStatus());
				ParkingSlots parkingSlots4= parkingSlotsRepo.save(parkingSlots);
				return slotdto(parkingSlots4);
			}else {
				throw new RuntimeException("slot name already exists");
			}
		}
		else {
//			throw new RuntimeException("id is not present");
			List<String> list=new ArrayList<>();
			list.add("id is not present");
			return list;
		} 
	}
	
	public ParkingSlotsDto slotdto(ParkingSlots p) {
		ParkingSlotsDto parkingSlotsDto=new ParkingSlotsDto();
		parkingSlotsDto.setSlotId(p.getId());
		parkingSlotsDto.setSlotName(p.getSlotName());
		parkingSlotsDto.setStatus(p.getStatus());
		parkingSlotsDto.setZoneId(p.getParkingZones().getId());
		parkingSlotsDto.setCreatedOn(p.getCreatedOn());
		parkingSlotsDto.setUpdatedOn(p.getUpdatedOn());
		return parkingSlotsDto;
	}
 	
	public List<Object> getAllByZoneId(long id) {
		ParkingZones parkingZones=parkingZonesRepo.findById(id);
		if(parkingZones!=null) {
			List<ParkingSlots> parkingSlots= parkingSlotsRepo.findAllByparkingZonesId(id);
			List<Object> parkingSlotsDto=new ArrayList();
			for (ParkingSlots parkingSlotsDto2 : parkingSlots) {
				ParkingSlotsDto parkingSlotsDto3= slotdto(parkingSlotsDto2);
				parkingSlotsDto.add(parkingSlotsDto3);
			}
			return parkingSlotsDto;
		}
		else {
//			throw new RuntimeException("zone id is not present");
			List<Object> list=new ArrayList<>();
			list.add("Zone id is not present");
			return list;
		}
	}
	
	public List<HistoryDto> pastHistory(long id){
		ParkingSlots parkingSlots= parkingSlotsRepo.findById(id);
		if(parkingSlots!=null) {
			List<ParkingOrder> parkingOrder= parkingOrderRepo.findAllByParkingSlotId(id);
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
			throw new RuntimeException("slot is not present");
		}
	}
	
	public List<HistoryDto> activeHistory(long id){
		ParkingSlots parkingSlots= parkingSlotsRepo.findById(id);
		if(parkingSlots!=null) {
			List<ParkingOrder> parkingOrder= parkingOrderRepo.findAllByParkingSlotId(id);
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
			throw new RuntimeException("slot is not present");
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

