package com.carchargingalgo.model;

/**
 * This is a Gas Station class which will be used to create object of individual staiton inputs.
 *
 */
public class GasStation {

	private int stationId;
	private int stationType;
	private int noOfCustomersHolding;
	
	public GasStation(int stationId, int stationType) {
		stationType--;
		setStationId(stationId);
		setStationType(stationType);
	}
	
	/**
	 * @return the stationId
	 */
	public int getStationId() {
		return stationId;
	}
	/**
	 * @param stationId the stationId to set
	 */
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}
	/**
	 * @return the stationType
	 */
	public int getStationType() {
		return stationType;
	}
	/**
	 * @param stationType the stationType to set
	 */
	public void setStationType(int stationType) {
		this.stationType = stationType;
	}

	/**
	 * @return the noOfCustomersHolding
	 */
	public int getNoOfCustomersHolding() {
		return noOfCustomersHolding;
	}

	/**
	 * @param noOfCustomersHolding the noOfCustomersHolding to set
	 */
	public void setNoOfCustomersHolding(int noOfCustomersHolding) {
		this.noOfCustomersHolding = noOfCustomersHolding;
	}
	
}
