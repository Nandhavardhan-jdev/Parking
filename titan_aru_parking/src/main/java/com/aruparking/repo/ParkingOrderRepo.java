package com.aruparking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aruparking.model.ParkingOrder;

@Repository
public interface ParkingOrderRepo extends JpaRepository<ParkingOrder, Long>{

	List<ParkingOrder> findAllByParkingUserId(long id);

	List<ParkingOrder> findAllByParkingSlotParkingZonesId(long id);

	List<ParkingOrder> findAllByParkingSlotId(long id);

}
