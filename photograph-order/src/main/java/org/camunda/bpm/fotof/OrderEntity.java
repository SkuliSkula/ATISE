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
	protected boolean showUp;

	// Booking data
	protected String sessionStart;
	protected String timeOfDay;
	protected double duration;
	protected String shootingType;
	protected String shootingLocation;

	// Technician work
	protected boolean clean;
	protected boolean edit;
	
	// Customer gallery  aep = additional editing picture
	protected boolean aep1;
	protected String aep1Info;
	protected boolean aep2;
	protected String aep2Info;
	protected boolean aep3;
	protected String aep3Info;
	protected boolean wantsPickUp;
	protected boolean wantsPrintOuts;	
	
	// technician work
	protected boolean uploadToDropbox;
	protected boolean printPhotos;
	protected boolean dropPhotosToCSR;
	
	public boolean isPrintPhotos() {
		return printPhotos;
	}

	public void setPrintPhotos(boolean printPhotos) {
		this.printPhotos = printPhotos;
	}

	public boolean isDropPhotosToCSR() {
		return dropPhotosToCSR;
	}

	public void setDropPhotosToCSR(boolean dropPhotosToCSR) {
		this.dropPhotosToCSR = dropPhotosToCSR;
	}

	public boolean isUploadToDropbox() {
		return uploadToDropbox;
	}

	public void setUploadToDropbox(boolean uploadToDropbox) {
		this.uploadToDropbox = uploadToDropbox;
	}

	public String getAep1Info() {
		return aep1Info;
	}

	public void setAep1Info(String aep1Info) {
		this.aep1Info = aep1Info;
	}

	public String getAep2Info() {
		return aep2Info;
	}

	public void setAep2Info(String aep2Info) {
		this.aep2Info = aep2Info;
	}

	public String getAep3Info() {
		return aep3Info;
	}

	public void setAep3Info(String aep3Info) {
		this.aep3Info = aep3Info;
	}
	
	public boolean isWantsPickUp() {
		return wantsPickUp;
	}

	public void setWantsPickUp(boolean wantsPickUp) {
		this.wantsPickUp = wantsPickUp;
	}

	public boolean isWantsPrintOuts() {
		return wantsPrintOuts;
	}

	public void setWantsPrintOuts(boolean wantsPrintOuts) {
		this.wantsPrintOuts = wantsPrintOuts;
	}

	public boolean isAep1() {
		return aep1;
	}

	public void setAep1(boolean aep1) {
		this.aep1 = aep1;
	}

	public boolean isAep2() {
		return aep2;
	}

	public void setAep2(boolean aep2) {
		this.aep2 = aep2;
	}

	public boolean isAep3() {
		return aep3;
	}

	public void setAep3(boolean aep3) {
		this.aep3 = aep3;
	}

	public boolean isClean() {
		return clean;
	}

	public void setClean(boolean clean) {
		this.clean = clean;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

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
	
	public void setShowUp(boolean showUp) {
		this.showUp = showUp;
	}
	
	public boolean getShowUp() {
		return showUp;
	}
	
}
