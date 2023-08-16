package com.aruparking.dto;

import java.util.Date;

import com.aruparking.model.ParkingZones;

public class ParkingSlotsDto {
	
	private long slotId;
	private String slotName;
	private int status;
	private Date createdOn;
	private Date updatedOn;
	private long zoneId;
	
	public long getSlotId() {
		return slotId;
	}
	public void setSlotId(long slotId) {
		this.slotId = slotId;
	}
	public String getSlotName() {
		return slotName;
	}
	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public long getZoneId() {
		return zoneId;
	}
	public void setZoneId(long zoneID) {
		this.zoneId = zoneID;
	}
	
	
	
}
