package com.aruparking.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aruparking.dto.ParkingVehicleDto;
import com.aruparking.model.ParkingUser;
import com.aruparking.model.ParkingVehicle;
import com.aruparking.repo.ParkingUserRepo;
import com.aruparking.repo.ParkingVehicleRepo;
import com.aruparking.service.UserServiceVehicle;

@Service
public class UserServiceVehicleImpl implements UserServiceVehicle{

	@Autowired
	ParkingVehicleRepo parkingVehicleRepo;
	@Autowired
	ParkingUserRepo parkingUserRepo;
	Map<Object, Object> map=new HashMap<>();
	
	public ParkingVehicleDto add(ParkingVehicleDto parkingVehicleDto) {
		ParkingUser parkingUser= parkingUserRepo.findById(parkingVehicleDto.getUserId());
		if(parkingUser!=null) {
			ParkingVehicle parkingVehicle= parkingVehicleRepo.findByParkingUserIdAndVehicleNo(parkingVehicleDto.getUserId(), parkingVehicleDto.getVehicleNo());
			if(parkingVehicle==null) {
				if(parkingVehicleDto.isDefaultVehicle()==false) {
					ParkingVehicle parkingVehicle2=new ParkingVehicle();
					parkingVehicle2.setParkingUser(parkingUser);
					parkingVehicle2.setVehicleNo(parkingVehicleDto.getVehicleNo());
					parkingVehicle2.setVehicleName(parkingVehicleDto.getVehicleName());
					parkingVehicle2.setDefaultVehicle(parkingVehicleDto.isDefaultVehicle());
					parkingVehicle2.setFavVehicle(parkingVehicleDto.isFavVehicle());
					parkingVehicle2.setStatus(1);
					 ParkingVehicle parkingVehicle3= parkingVehicleRepo.save(parkingVehicle2);
					return vehicleDto(parkingVehicle3);
				}else {
					ParkingVehicle parkingVehicle2= parkingVehicleRepo.findByDefaultVehicleAndParkingUserId(parkingVehicleDto.isDefaultVehicle(), parkingVehicleDto.getUserId());
					if (parkingVehicle2==null) {
						ParkingVehicle parkingVehicle3=new ParkingVehicle();
						parkingVehicle3.setParkingUser(parkingUser);
						parkingVehicle3.setVehicleNo(parkingVehicleDto.getVehicleNo());
						parkingVehicle3.setVehicleName(parkingVehicleDto.getVehicleName());
						parkingVehicle3.setDefaultVehicle(parkingVehicleDto.isDefaultVehicle());
						parkingVehicle3.setFavVehicle(parkingVehicleDto.isFavVehicle());
						parkingVehicle3.setStatus(1);
						 ParkingVehicle parkingVehicle4= parkingVehicleRepo.save(parkingVehicle3);
						return vehicleDto(parkingVehicle4);
					}else {
						parkingVehicle2.setDefaultVehicle(false);
						ParkingVehicle parkingVehicle3=new ParkingVehicle();
						parkingVehicle3.setParkingUser(parkingUser);
						parkingVehicle3.setVehicleNo(parkingVehicleDto.getVehicleNo());
						parkingVehicle3.setVehicleName(parkingVehicleDto.getVehicleName());
						parkingVehicle3.setDefaultVehicle(parkingVehicleDto.isDefaultVehicle());
						parkingVehicle3.setFavVehicle(parkingVehicleDto.isFavVehicle());
						parkingVehicle3.setStatus(1);
						 ParkingVehicle parkingVehicle4= parkingVehicleRepo.save(parkingVehicle3);
						return vehicleDto(parkingVehicle4);
					}
				}
			}else {
				throw new RuntimeException("vehicle number is already exists for the user");
			}
		}else {
			throw new RuntimeException("UserId is not present");
		}
		
	}

	
	public ParkingVehicleDto update(ParkingVehicleDto parkingVehicleDto) {
		ParkingVehicle parkingVehicle= parkingVehicleRepo.findById(parkingVehicleDto.getId());
		if (parkingVehicle!=null) {
			if(parkingVehicle.getVehicleNo().equals(parkingVehicleDto.getVehicleNo())) {
				if(parkingVehicleDto.isDefaultVehicle()==false) {
					parkingVehicle.setVehicleName(parkingVehicleDto.getVehicleName());
					parkingVehicle.setDefaultVehicle(parkingVehicleDto.isDefaultVehicle());
					parkingVehicle.setFavVehicle(parkingVehicleDto.isFavVehicle());
					ParkingVehicle parkingVehicle2= parkingVehicleRepo.save(parkingVehicle);
					return vehicleDto(parkingVehicle2);
				}else {
					ParkingVehicle parkingVehicle3= parkingVehicleRepo.findByDefaultVehicleAndParkingUserId(parkingVehicleDto.isDefaultVehicle(), parkingVehicleDto.getUserId());
					if(parkingVehicle3==null) {
						parkingVehicle.setVehicleName(parkingVehicleDto.getVehicleName());
						parkingVehicle.setDefaultVehicle(parkingVehicleDto.isDefaultVehicle());
						parkingVehicle.setFavVehicle(parkingVehicleDto.isFavVehicle());
						ParkingVehicle parkingVehicle2= parkingVehicleRepo.save(parkingVehicle);
						return vehicleDto(parkingVehicle2);
					}else {
						parkingVehicle3.setDefaultVehicle(false);
						parkingVehicle.setVehicleName(parkingVehicleDto.getVehicleName());
						parkingVehicle.setDefaultVehicle(parkingVehicleDto.isDefaultVehicle());
						parkingVehicle.setFavVehicle(parkingVehicleDto.isFavVehicle());
						ParkingVehicle parkingVehicle2= parkingVehicleRepo.save(parkingVehicle);
						return vehicleDto(parkingVehicle2);
					}
				}
			}
			//vehicle no not equal
			else {
				ParkingVehicle parkingVehicle2= parkingVehicleRepo.findByParkingUserIdAndVehicleNo(parkingVehicleDto.getUserId(), parkingVehicleDto.getVehicleNo());
				if(parkingVehicle2==null) {
					if(parkingVehicleDto.isDefaultVehicle()==false) {
						parkingVehicle.setVehicleNo(parkingVehicleDto.getVehicleNo());
						parkingVehicle.setVehicleName(parkingVehicleDto.getVehicleName());
						parkingVehicle.setDefaultVehicle(parkingVehicleDto.isDefaultVehicle());
						parkingVehicle.setFavVehicle(parkingVehicleDto.isFavVehicle());
						ParkingVehicle parkingVehicle3= parkingVehicleRepo.save(parkingVehicle);
						return vehicleDto(parkingVehicle3);
					}else {
						ParkingVehicle parkingVehicle3= parkingVehicleRepo.findByDefaultVehicleAndParkingUserId(parkingVehicleDto.isDefaultVehicle(), parkingVehicleDto.getUserId());
						if(parkingVehicle3==null) {
							parkingVehicle.setVehicleNo(parkingVehicleDto.getVehicleNo());
							parkingVehicle.setVehicleName(parkingVehicleDto.getVehicleName());
							parkingVehicle.setDefaultVehicle(parkingVehicleDto.isDefaultVehicle());
							parkingVehicle.setFavVehicle(parkingVehicleDto.isFavVehicle());
							ParkingVehicle parkingVehicle4= parkingVehicleRepo.save(parkingVehicle);
							return vehicleDto(parkingVehicle4);
						}else {
							parkingVehicle3.setDefaultVehicle(false);
							parkingVehicle.setVehicleNo(parkingVehicleDto.getVehicleNo());
							parkingVehicle.setVehicleName(parkingVehicleDto.getVehicleName());
							parkingVehicle.setDefaultVehicle(parkingVehicleDto.isDefaultVehicle());
							parkingVehicle.setFavVehicle(parkingVehicleDto.isFavVehicle());
							ParkingVehicle parkingVehicle4= parkingVehicleRepo.save(parkingVehicle);
							return vehicleDto(parkingVehicle4);
						}
					}
				}else {
					throw new RuntimeException("vehicle no already exists for the user");
				}
			}
		}else {
			throw new RuntimeException("parkingUserVehicleAccount does not exist");
		}
		
	}
	
	
	public Object delete(long id) {
		ParkingVehicle parkingVehicle= parkingVehicleRepo.findById(id);
		if (parkingVehicle!=null) {
			parkingVehicle.setStatus(0);
			parkingVehicleRepo.save(parkingVehicle);
			map.clear();
			map.put("status", "deleted successfully");
			return map;
		}else {
//			throw new RuntimeException("account doesn't exists");
			map.clear();
			map.put("error", "account doesn't exists");
			return map;
		}
	}
	
	public List<ParkingVehicleDto> savedAc(long id) {
		ParkingUser parkingUser= parkingUserRepo.findById(id);
		if(parkingUser!=null) {
			List<ParkingVehicle> parkingVehicle= parkingVehicleRepo.findAllByParkingUserId(id);
			List<ParkingVehicleDto> parkingVehicleDtos=new ArrayList<>();
			for (ParkingVehicle parkingVehicle2 : parkingVehicle) {
				ParkingVehicleDto parkingVehicleDto= vehicleDto(parkingVehicle2);
				parkingVehicleDtos.add(parkingVehicleDto);
			}
			return parkingVehicleDtos;
		}else {
			throw new RuntimeException("user id does not exists");
		}
		
	}
	
	public List<ParkingVehicleDto> favAc(long id){
		ParkingUser parkingUser= parkingUserRepo.findById(id);
		if(parkingUser!=null) {
			List<ParkingVehicle> parkingVehicles= parkingVehicleRepo.findAllByParkingUserId(id);
			List<ParkingVehicleDto> parkingVehicleDtos=new ArrayList<>();
			for (ParkingVehicle parkingVehicles2 : parkingVehicles) {
				if(parkingVehicles2.isFavVehicle()==true) {
					ParkingVehicleDto parkingVehicleDto= vehicleDto(parkingVehicles2);
					parkingVehicleDtos.add(parkingVehicleDto);
				}
			}
			return parkingVehicleDtos;
		}else {
			throw new RuntimeException("user Id does not exists");
		}
		
	}
	
	public ParkingVehicleDto defaultAc(long id) {
		ParkingUser parkingUser= parkingUserRepo.findById(id);
		ParkingVehicleDto parkingVehicleDto=new ParkingVehicleDto();
		if(parkingUser!=null) {
			List<ParkingVehicle> parkingVehicles= parkingVehicleRepo.findAllByParkingUserId(id);
			for (ParkingVehicle parkingVehicles2 : parkingVehicles) {
				if(parkingVehicles2.isDefaultVehicle()==true) {
					parkingVehicleDto= vehicleDto(parkingVehicles2);
				}
			}
		}else {
			throw new RuntimeException("user id does not exists");
		}
		return parkingVehicleDto;
	}
	
	public ParkingVehicleDto vehicleDto(ParkingVehicle parkingVehicle) {
		ParkingVehicleDto parkingVehicleDto2=new ParkingVehicleDto();
		parkingVehicleDto2.setId(parkingVehicle.getId());
		parkingVehicleDto2.setUserId(parkingVehicle.getParkingUser().getId());
		parkingVehicleDto2.setVehicleNo(parkingVehicle.getVehicleNo());
		parkingVehicleDto2.setVehicleName(parkingVehicle.getVehicleName());
		parkingVehicleDto2.setDefaultVehicle(parkingVehicle.isDefaultVehicle());
		parkingVehicleDto2.setFavVehicle(parkingVehicle.isFavVehicle());
		parkingVehicleDto2.setStatus(parkingVehicle.getStatus());
		parkingVehicleDto2.setCreatedOn(parkingVehicle.getCreatedOn());
		parkingVehicleDto2.setLastUpdatedOn(parkingVehicle.getLastUpdatedOn());
		return parkingVehicleDto2;
	}
}
