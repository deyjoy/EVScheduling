package com.carchargingalgo.data;

import java.util.ArrayList;

import com.carchargingalgo.model.CustomerVehicle;
import com.carchargingalgo.model.GasStation;
import com.carchargingalgo.model.Station;
import com.carchargingalgo.model.Vehicle;

public class Data {

	public static String file_path = "ExcelFile/testdata3.xlsx";
	
	public static final String LINK_PATH = "ExcelFile/Output.xlsx";
	
	// Customer Input
	/**
	 * Holds customer 
	 */
	public static ArrayList<CustomerVehicle> customer = new ArrayList<CustomerVehicle>();
	
	
	// Charging Point Input
	/**
	 * Holds Station Id from charging point input
	 */
	public static ArrayList<GasStation> stationChargingPoint = new ArrayList<GasStation>();
	
	
	// Types of Vehicles
	/**
	 * Holds Type of Vehicles
	 */
	public static ArrayList<Vehicle> typesOfVehicle = new ArrayList<Vehicle>();
	
	// Types of Stations
	/**
	 * Holds Type of Stations
	 */
	public static ArrayList<Station> typesOfStation = new ArrayList<Station>();
	
}
