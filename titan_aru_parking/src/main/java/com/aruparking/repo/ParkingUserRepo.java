package com.aruparking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aruparking.model.ParkingUser;

@Repository
public interface ParkingUserRepo extends JpaRepository<ParkingUser, Long>{
	
	public ParkingUser findById(long id);
}
