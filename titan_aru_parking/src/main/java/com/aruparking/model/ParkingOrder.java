package com.aruparking.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "PARKING_ORDER")
public class ParkingOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "AMOUNT")
	private BigDecimal amount;
	
	@Column(name = "VEHICLE_NO")
	private String vehicleNo;
	
	@Column(name = "CONTACT_NO")
	private String contactNo;
	
	@Column(name = "TRANSACTION_ID")
	private	String transactionId;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private	ParkingUser parkingUser;
	
	@ManyToOne
	@JoinColumn(name = "SLOT_ID")
	private ParkingSlots parkingSlot;
	
	@ManyToOne
	@JoinColumn(name = "FEE_ID")
	private ParkingFee parkingFee;
	
	@Column(name = "PARKING_START_TIME",updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date parkingStartTime;
	
	@Column(name = "PARKING_END_TIME")
//	@UpdateTimestamp
//	@Temporal(TemporalType.TIMESTAMP) 
	private Date parkingEndTime;
	
	@Column(name = "CREATED_ON",updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	@Column(name = "LAST_UPDATED_ON")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdatedOn;
	
	public ParkingFee getParkingFee() {
		return parkingFee;
	}
	public void setParkingFee(ParkingFee parkingFee) {
		this.parkingFee = parkingFee;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public ParkingUser getParkingUser() {
		return parkingUser;
	}
	public void setParkingUser(ParkingUser parkingUser) {
		this.parkingUser = parkingUser;
	}
	public ParkingSlots getParkingSlot() {
		return parkingSlot;
	}
	public void setParkingSlot(ParkingSlots parkingSlot) {
		this.parkingSlot = parkingSlot;
	}
	public Date getParkingStartTime() {
		return parkingStartTime;
	}
	public void setParkingStartTime(Date parkingStartTime) {
		this.parkingStartTime = parkingStartTime;
	}
	public Date getParkingEndTime() {
		return parkingEndTime;
	}
	public void setParkingEndTime(Date parkingEndTime) {
		this.parkingEndTime = parkingEndTime;
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
