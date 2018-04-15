package org.camunda.bpm.fotof;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

@Entity
public class OrderEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	protected Long id;

	@Version
	protected long version;

	// Customer data
	protected String fullName;
	protected String phoneNumber;
	protected String email;
	protected String shippingAddress;

	// Booking data
	protected String sessionStart;
	protected String timeOfDay;
	protected double duration;
	protected String shootingType;
	protected String shootingLocation;

	public String getShootingType() {
		return shootingType;
	}

	public void setShootingType(String shootingType) {
		this.shootingType = shootingType;
	}

	public String getShootingLocation() {
		return shootingLocation;
	}

	public void setShootingLocation(String shootingLocation) {
		this.shootingLocation = shootingLocation;
	}

	public boolean isSpecialEquipmentNeeded() {
		return isSpecialEquipmentNeeded;
	}

	public void setSpecialEquipmentNeeded(boolean isSpecialEquipmentNeeded) {
		this.isSpecialEquipmentNeeded = isSpecialEquipmentNeeded;
	}

	protected boolean isSpecialEquipmentNeeded; // This should be decided by the photographer

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getSessionStart() {
		return sessionStart;
	}

	public void setSessionStart(String sessionStart) {
		this.sessionStart = sessionStart;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public String getTimeOfDay() {
		return timeOfDay;
	}

	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}

}
