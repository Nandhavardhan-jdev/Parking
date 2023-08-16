package com.aruparking.serviceimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aruparking.dto.HistoryDto;
import com.aruparking.dto.ParkingOrderDto;
import com.aruparking.dto.ParkingUserDto;
import com.aruparking.model.ParkingOrder;
import com.aruparking.model.ParkingUser;
import com.aruparking.repo.ParkingOrderRepo;
import com.aruparking.repo.ParkingUserRepo;
import com.aruparking.service.UserServiceUser;

@Service
public class UserSeriveUserImpl implements UserServiceUser{
	@Autowired
	ParkingUserRepo parkingUserRepo;
	@Autowired
	ParkingOrderRepo parkingOrderRepo;
	
	public ParkingUserDto add(ParkingUser parkingUser) {
		ParkingUser parkingUser2=new ParkingUser();
		parkingUser2.setFirstName(parkingUser.getFirstName());
		parkingUser2.setLastName(parkingUser.getLastName());
		parkingUser2.setEmailId(parkingUser.getEmailId());
		parkingUser2.setDob(parkingUser.getDob());
		parkingUser2.setBalance(new BigDecimal("100.00"));
		ParkingUser parkingUser3= parkingUserRepo.save(parkingUser2);
		return userDto(parkingUser3);
	}
	
	public List<HistoryDto> getPastHistory(long id) {
		ParkingUser parkingUser= parkingUserRepo.findById(id);
		if(parkingUser!=null) {
			List<ParkingOrder> parkingOrder= parkingOrderRepo.findAllByParkingUserId(id);
			List<HistoryDto> historyDtos=new ArrayList<>();
			for (ParkingOrder parkingOrder1 : parkingOrder) {
				Date date=new Date();
				int comparsion = date.compareTo(parkingOrder1.getParkingEndTime());
				if(comparsion>0)	//pastHistory currentTime > endTime 
				{
				HistoryDto HistoryDto=userHistoryDto(parkingOrder1);
				historyDtos.add(HistoryDto);
				}
			}
			return historyDtos;
		}else {
			throw new RuntimeException("User id is not present");
		}
	}

	public List<HistoryDto> getActivetHistory(long id){
		ParkingUser parkingUser= parkingUserRepo.findById(id);
		if(parkingUser!=null) {
			List<ParkingOrder> parkingOrder= parkingOrderRepo.findAllByParkingUserId(id);
			List<HistoryDto> historyDtos=new ArrayList<>();
			for (ParkingOrder parkingOrder1 : parkingOrder) {
				Date date=new Date();
				int comparsion = date.compareTo(parkingOrder1.getParkingEndTime());
				if(comparsion<=0)	//activeHistory currentTime <= endTime
				{
				HistoryDto HistoryDto=userHistoryDto(parkingOrder1);
				historyDtos.add(HistoryDto);
				}
			}
			return historyDtos;
		}else {
			throw new RuntimeException("User id is not present");
		}
	}
	
	public HistoryDto userHistoryDto(ParkingOrder parkingOrder1) {
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

	public ParkingUserDto userDto(ParkingUser parkingUser3) {
		ParkingUserDto parkingUserDto=new ParkingUserDto();
		parkingUserDto.setId(parkingUser3.getId());
		parkingUserDto.setFirstName(parkingUser3.getFirstName());
		parkingUserDto.setLastName(parkingUser3.getLastName());
		parkingUserDto.setEmailId(parkingUser3.getEmailId());
		parkingUserDto.setDob(parkingUser3.getDob());
		parkingUserDto.setBalance(parkingUser3.getBalance());
		parkingUserDto.setCreatedOn(parkingUser3.getCreatedOn());
		parkingUserDto.setLastUpdatedOn(parkingUser3.getLastUpdatedOn());
		return parkingUserDto;
	}
}
