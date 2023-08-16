package com.aruparking.serviceimpl;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aruparking.dto.ParkingOrderDto;
import com.aruparking.model.ParkingFee;
import com.aruparking.model.ParkingOrder;
import com.aruparking.model.ParkingSlots;
import com.aruparking.model.ParkingUser;
import com.aruparking.repo.ParkingFeeRepo;
import com.aruparking.repo.ParkingOrderRepo;
import com.aruparking.repo.ParkingSlotsRepo;
import com.aruparking.repo.ParkingUserRepo;
import com.aruparking.service.UserServiceOrder;

@Service
public class UserServiceOrderImpl implements UserServiceOrder {

	@Autowired
	ParkingOrderRepo parkingOrderRepo;
	@Autowired
	ParkingUserRepo parkingUserRepo;
	@Autowired
	ParkingSlotsRepo parkingSlotsRepo;
	@Autowired
	ParkingFeeRepo parkingFeeRepo;
	
	public Object add(ParkingOrderDto parkingOrderDto) {
 		ParkingUser parkingUser= parkingUserRepo.findById(parkingOrderDto.getUserId());
		if(parkingUser!=null) {
			ParkingSlots parkingSlots= parkingSlotsRepo.findById(parkingOrderDto.getSlotId());
			if(parkingSlots!=null) {
				ParkingFee parkingFee= parkingFeeRepo.findById(parkingOrderDto.getFeeId());
				if(parkingFee!=null) {
					ParkingOrder parkingOrder=new ParkingOrder();
					BigDecimal bigDecimal= parkingFee.getAmount();
					BigDecimal bigDecimal2= parkingUser.getBalance();
					int comparsionResult=bigDecimal.compareTo(bigDecimal2);
					if(comparsionResult<=0) {
						BigDecimal balance=bigDecimal2.subtract(bigDecimal);
						parkingUser.setBalance(balance);
						parkingOrder.setAmount(parkingFee.getAmount());
						parkingOrder.setVehicleNo(parkingOrderDto.getVehicleNo());
						parkingOrder.setContactNo(parkingOrderDto.getContactNo());
						parkingOrder.setParkingUser(parkingUser);
						parkingOrder.setParkingSlot(parkingSlots);
						parkingOrder.setParkingFee(parkingFee);
						parkingOrder.setDescription("Done");  
						
						Date date =new Date();
						Date endtime= DateUtils.addHours(date, Integer.parseInt(parkingFee.getTiming()));
						parkingOrder.setParkingEndTime(endtime);
						
						parkingOrder.setTransactionId(UUID.randomUUID().toString().replace("-", ""));
						ParkingOrder parkingOrder2= parkingOrderRepo.save(parkingOrder);
						return orderDto(parkingOrder2);
					}else {
						throw new RuntimeException("Insufficient funds");
					}
				}else {
					throw new RuntimeException("Fee id is not present");
				}
			}else {
				throw new RuntimeException("slot id not present");
			}
		}else {
			throw new RuntimeException("user id not present");
		}
	}

	private ParkingOrderDto orderDto(ParkingOrder parkingOrder2) {
		ParkingOrderDto parkingOrderDto=new ParkingOrderDto();
		parkingOrderDto.setMessage("Payment Successful");
		parkingOrderDto.setAmount(parkingOrder2.getParkingFee().getAmount());
		parkingOrderDto.setId(parkingOrder2.getId());
		parkingOrderDto.setTransactionId(parkingOrder2.getTransactionId());
		return parkingOrderDto;
	}
	
}
