package com.aruparking.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "PARKING_VEHICLE")
public class ParkingVehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "VEHICLE_NO")
	private String vehicleNo;
	
	@Column(name = "VEHICLE_NAME")
	private String vehicleName;
	
	@Column(name = "DEFAULT_VEHICLE")
	private boolean defaultVehicle;
	
	@Column(name = "FAV_VEHICLE")
	private boolean favVehicle;
	
	@Column(name = "STATUS")
	private int status;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private ParkingUser parkingUser;
	
	@Column(name = "CREATED_ON", updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	@Column(name = "LAST_UPDATED_ON")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdatedOn;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public boolean isDefaultVehicle() {
		return defaultVehicle;
	}

	public void setDefaultVehicle(boolean defaultVehicle) {
		this.defaultVehicle = defaultVehicle;
	}

	public boolean isFavVehicle() {
		return favVehicle;
	}

	public void setFavVehicle(boolean favVehicle) {
		this.favVehicle = favVehicle;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ParkingUser getParkingUser() {
		return parkingUser;
	}

	public void setParkingUser(ParkingUser parkingUser) {
		this.parkingUser = parkingUser;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
	
	
}
