package com.aruparking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aruparking.model.ParkingFee;

public interface ParkingFeeRepo extends JpaRepository<ParkingFee, Long>{

	ParkingFee findById(long id);
}
