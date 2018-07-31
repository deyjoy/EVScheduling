package com.carchargingalgo.model;

/**
 * This is Vehicle model class.
 * From Vehicle model class I made three object, i.e, Nissan Leaf, Chevy Bolt, Tesla M3
 *
 */
public class Vehicle {
	
	private int vehicleType;
	private double totalTimeNeededInGasSation [] = {-1, -1, -1, -1}; // Level 2, CHAdeMo, J1772 combo, Supercharger
	private String vehicleName;
	
	/**
	 * This is a constructor which takes vehicle type.
	 * @param vehicleType
	 */
	public Vehicle(int vehicleType) {
		switch (vehicleType) {
		case 0:
			setVehicleName("Nissan Leaf");
			break;
		case 1:
			setVehicleName("Chevy Bolt");
			break;
		case 2:
			setVehicleName("Tesla M3");
			break;
		default:
			break;
		}
		setVehicleType(vehicleType);
	}
	
	/**
	 * Sets all Time for the Vehicle to Refill in different Station.
	 * @param Level2Station
	 * @param CHAdeMoStation
	 * @param J1772ComboStation
	 * @param superChargerStation
	 */
	public void setTimeNeededToRefillVehicleInDifferentStation(double Level2Station, double CHAdeMoStation, double J1772ComboStation, double superChargerStation) {
		setTimeNeededForGasStation(Level2Station, 0);
		setTimeNeededForGasStation(CHAdeMoStation, 1);
		setTimeNeededForGasStation(J1772ComboStation, 2);
		setTimeNeededForGasStation(superChargerStation, 3);
	}
	
	/**
	 * This method sets time needed for gas station to refill this vehicle.
	 * @param timeNeeded
	 * @param gasStationIndex
	 */
	public void setTimeNeededForGasStation(double timeNeeded, int gasStationIndex) {
		totalTimeNeededInGasSation[gasStationIndex] = timeNeeded;
	}
	
	/**
	 * This method sets Vehicle Type
	 * @param typeIndex
	 */
	public void setVehicleType(int typeIndex) {
		vehicleType = typeIndex;
	}
	
	/**
	 * This method gets the Time Needed for the vehicle to refill gas for a particular Station.
	 * @param gasStationTypeIndex
	 * @return double
	 */
	public double getTimeNeeded(int gasStationTypeIndex) {
		return totalTimeNeededInGasSation[gasStationTypeIndex];
	}
	
	/**
	 * This method returns Vehicle Type
	 * @return int
	 */
	public int getVehicleType() {
		return vehicleType;
	}

	/**
	 * @return the vehicleName
	 */
	public String getVehicleName() {
		return vehicleName;
	}

	/**
	 * @param vehicleName the vehicleName to set
	 */
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
 	
}
