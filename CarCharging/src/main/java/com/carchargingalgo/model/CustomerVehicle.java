package com.carchargingalgo.model;

import com.carchargingalgo.data.Data;

/**
 * This is a customer vehicle class which will be used in creating object for individual customer input.
 *
 */
public class CustomerVehicle {

	private int customerId;
	private int [] preferredStartTime;
	private int [] preferredEndTime;
	private int mileTotravel;
	private int eVType;
	private boolean isPossibility;
	private int [] timeRequiredToRefillForDifferentStation = {-1, -1, -1, -1};
	public boolean isNullValue = false;
	
	/**
	 * This is a Customer Vehicle constructor.
	 * @param customerId
	 * @param preferredStartTime
	 * @param preferredEndTime
	 * @param mileTotravel
	 * @param eVType
	 */
	public CustomerVehicle(int customerId, int [] preferredStartTime, int [] preferredEndTime, int mileTotravel, int eVType) {
		eVType--;
		setCustomerId(customerId);
		setPreferredStartTime(preferredStartTime);
		setPreferredEndTime(preferredEndTime);
		setMileTotravel(mileTotravel);
		setEVType(eVType);
		for (int i = 0; i < Data.typesOfStation.size(); i++) {
			if(Data.typesOfStation.get(i).getIsCompatible(eVType)) {
				timeRequiredToRefillForDifferentStation[i] = Math.round((mileTotravel * 60) / Data.typesOfStation.get(i).getMilesPerHour(eVType));
			}
		}
	}
	
	public CustomerVehicle() {
		
	}
	
	public CustomerVehicle(boolean isTrue) {
		isNullValue = isTrue;
	}
	
	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the preferredStartTime
	 */
	public int [] getPreferredStartTime() {
		return preferredStartTime;
	}
	/**
	 * @param preferredStartTime the preferredStartTime to set
	 */
	public void setPreferredStartTime(int [] preferredStartTime) {
		this.preferredStartTime = preferredStartTime;
	}
	/**
	 * @return the preferredEndTime
	 */
	public int [] getPreferredEndTime() {
		return preferredEndTime;
	}
	/**
	 * @param preferredEndTime the preferredEndTime to set
	 */
	public void setPreferredEndTime(int [] preferredEndTime) {
		this.preferredEndTime = preferredEndTime;
	}
	/**
	 * @return the mileTotravel
	 */
	public int getMileTotravel() {
		return mileTotravel;
	}
	/**
	 * @param mileTotravel the mileTotravel to set
	 */
	public void setMileTotravel(int mileTotravel) {
		this.mileTotravel = mileTotravel;
	}
	/**
	 * @return the eVType
	 */
	public int getEVType() {
		return eVType;
	}
	/**
	 * @param eVType the eVType to set
	 */
	public void setEVType(int eVType) {
		this.eVType = eVType;
	}
	
	/**
	 * @return the isPossibility
	 */
	public boolean isPossibility() {
		return isPossibility;
	}

	/**
	 * @param isPossibility the isPossibility to set
	 */
	public void setPossibility(boolean isPossibility) {
		this.isPossibility = isPossibility;
	}
	
	/**
	 * Setting Time Required to refill in Particular Station
	 * @param stationIndex
	 * @param timeRequired
	 */
	public void setTimeRequiredToRefillInStationAt(int stationIndex, int timeRequired) {
		timeRequiredToRefillForDifferentStation[stationIndex] = timeRequired;
	}
	
	/**
	 * This method gives the time needed to refill in this station.
	 * @param index
	 * @return
	 */
	public int getTimeRequiredToRefilleInStationAt(int index) {
		return timeRequiredToRefillForDifferentStation[index];
	}
	
	public int getActualEVType() {
		return getEVType() + 1;
	}
	
	public static void generateVehicle() {
		// Nissan
		Vehicle nissan = new Vehicle(0);
		Data.typesOfVehicle.add(nissan);
	
		// Chevy
		Vehicle chevy = new Vehicle(1);
		Data.typesOfVehicle.add(chevy);
		
		// Tesla
		Vehicle tesla = new Vehicle(2);
		Data.typesOfVehicle.add(tesla);
		
		for (int i = 0; i < Data.typesOfVehicle.size(); i++) {
			for (int j = 0; j < Data.typesOfStation.size(); j++) {
				if(Data.typesOfStation.get(j).getIsCompatible(i)) {
					Data.typesOfVehicle.get(i).setTimeNeededForGasStation(Data.typesOfStation.get(j).getMilesPerHour(i), j);
				}
				
			}
		}
	}
	
}
