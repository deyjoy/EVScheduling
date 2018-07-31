package com.carchargingalgo.model;

import com.carchargingalgo.data.Data;

/**
 * This is a Station Model Class
 * From Station model class I made four objects of Station , i.e, Level 2, CHAdeMo, J1772 combo, Supercharger.
 */
public class Station {

	private boolean [] compatible = {false, false, false}; // Nissan Leaf, Chevy Bolt, Tesla M3
	
	private int [] refillMilePerHour = {-1, -1, -1}; // For individual vehicles
	
	private int stationType;
	
	private String stationName;
	
	/**
	 * This is a station constructor which takes station type as int format.
	 * @param stationType
	 */
	public Station(int stationType) {
		switch (stationType) {
		case 0:
			setStationName("Level2");
			break;
		case 1:
			setStationName("CHAdeMo");
			break;
		case 2:
			setStationName("J1772 combo");
			break;
		case 3:
			setStationName("Superchargers");
			break;
		default:
			break;
		}
		this.stationType = stationType;
	}
	
	/**
	 * This method sets if a vehicle is Compatible with the Station or not.
	 * @param isCompatible
	 * @param compatibleWithWhat
	 */
	public void setCompatibility(boolean isCompatible, int compatibleWithWhat) {
		compatible[compatibleWithWhat] = isCompatible;
	}
	
	/**
	 * This method sets miles per hour value for each vehicles.
	 * @param milePerHour
	 * @param index
	 */
	public void setRefillMilePerHour(int milePerHour, int index) {
		refillMilePerHour[index] = milePerHour;
	}
	
	/**
	 * This method sets the both the value if the vehicle is compatible with the station or not and miles per hour it takes to refill.
	 * @param isCompatible
	 * @param milePerHour
	 * @param index
	 */
	public void setVehicleInStation(boolean isCompatible, int milePerHour, int index) {
		setCompatibility(isCompatible, index);
		setRefillMilePerHour(milePerHour, index);
	}
	
	/**
	 * This method returns if the vehicle is compatible or not.
	 * @param vehicleIndex
	 * @return boolean 
	 */
	public boolean getIsCompatible(int vehicleIndex) {
		return compatible[vehicleIndex];
	}
	
	/**
	 * This method returns miles per hour it takes to refill for a vehicle in this particular station.
	 * @param vehicleIndex
	 * @return int
	 */
	public int getMilesPerHour(int vehicleIndex) {
		return refillMilePerHour[vehicleIndex];
	}

	/**
	 * This method return Station Type.
	 * @return the stationType
	 */
	public int getStationType() {
		return stationType;
	}

	/**
	 * This method sets station type.
	 * @param stationType the stationType to set
	 */
	public void setStationType(int stationType) {
		this.stationType = stationType;
	}

	/**
	 * @return the stationName
	 */
	public String getStationName() {
		return stationName;
	}

	/**
	 * @param stationName the stationName to set
	 */
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	
public static void generateStation() {
		
		//Level 2 
		Station level2 = new Station(0);
		level2.setVehicleInStation(true, 22, 0);
		level2.setVehicleInStation(true, 24, 1);
		level2.setVehicleInStation(true, 25, 2);
		Data.typesOfStation.add(level2);
		
		//CHAdeMo 
		Station chademo = new Station(1);
		chademo.setVehicleInStation(true, 73, 0);
		chademo.setVehicleInStation(false, -1, 1);
		chademo.setVehicleInStation(true, 85, 2);
		Data.typesOfStation.add(chademo);
		
		//J1772 combo
		Station j1772 = new Station(2);
		j1772.setVehicleInStation(false, -1, 0);
		j1772.setVehicleInStation(true, 130, 1);
		j1772.setVehicleInStation(false, -1, 2);
		Data.typesOfStation.add(j1772);
		
		//Supercharger
		Station supercharger = new Station(3);
		supercharger.setVehicleInStation(false, -1, 0);
		supercharger.setVehicleInStation(false, -1, 1);
		supercharger.setVehicleInStation(true, 160, 2);
		Data.typesOfStation.add(supercharger);
		
	}
		
}
