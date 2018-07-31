package com.carchargingalgo.model;

public class CustomerHolder {

	CustomerVehicle cv;
	int index;
	int [] refillTime;
	int duration;
	int stationType;
	public int stationId = -1;
	
	public int getStationType() {
		return stationType;
	}

	public void setStationType(int stationType) {
		this.stationType = stationType;
	}

	public CustomerVehicle getCustomer() {
		return cv;
	}

	public void setCustomer(CustomerVehicle cv) {
		this.cv = cv;
	}

	public int[] getRefillTime() {
		return refillTime;
	}

	public void setRefillTime(int[] refillTime) {
		this.refillTime = refillTime;
	}
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public CustomerHolder(CustomerVehicle cv, int [] refillTime, int duration, int index, int stationType, int stationId){
		setCustomer(cv);
		setRefillTime(refillTime);
		setDuration(duration);
		setIndex(index);
		setStationType(stationType);
		this.stationId = stationId;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getActualStationType() {
		return getStationType() + 1;
	}
	
}
