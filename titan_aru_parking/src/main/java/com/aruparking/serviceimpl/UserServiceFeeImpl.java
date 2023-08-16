package com.aruparking.serviceimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aruparking.dto.ParkingFeeDto;
import com.aruparking.model.ParkingFee;
import com.aruparking.repo.ParkingFeeRepo;
import com.aruparking.service.UserServiceFee;

@Service
public class UserServiceFeeImpl implements UserServiceFee {

	@Autowired
	ParkingFeeRepo parkingFeeRepo;

	public ParkingFeeDto add(ParkingFeeDto parkingFeeDto) {
		ParkingFee parkingFee=new ParkingFee();
		BigDecimal bigDecimal= new BigDecimal(parkingFeeDto.getTiming());
		BigDecimal bigDecimal2=new BigDecimal("10.00");
		BigDecimal result=bigDecimal.multiply(bigDecimal2);
		parkingFee.setAmount(result);
		parkingFee.setTiming(parkingFeeDto.getTiming());
		ParkingFee parkingFee2= parkingFeeRepo.save(parkingFee);
		return feeDto(parkingFee2);
	}

	public Object getAll() {
		List<ParkingFee> parkingFee= parkingFeeRepo.findAll();
		if(parkingFee!=null) {
			List<ParkingFeeDto> parkingFeedto=new ArrayList<>();
			for (ParkingFee parkingFee1 : parkingFee) {
				ParkingFeeDto parkingFeeDto=new ParkingFeeDto();
				ParkingFeeDto parkingFeeDto1= feeDto(parkingFee1);
				parkingFeedto.add(parkingFeeDto1);
			}
			return parkingFeedto;
		}else {
			throw new RuntimeException("no data present");
		}
	}
	
	public ParkingFeeDto feeDto(ParkingFee parkingFee) {
		ParkingFeeDto parkingFeeDto=new ParkingFeeDto();
		parkingFeeDto.setId(parkingFee.getId());
		parkingFeeDto.setTiming(parkingFee.getTiming());
		parkingFeeDto.setAmount(parkingFee.getAmount());
		parkingFeeDto.setCreatedOn(parkingFee.getCreatedOn());
		parkingFeeDto.setLastUpdatedOn(parkingFee.getLastUpdatedOn());
		return parkingFeeDto;
	}
}
