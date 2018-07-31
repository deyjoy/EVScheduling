package com.carchargingalgo.algo;

import java.util.ArrayList;

import com.carchargingalgo.data.Data;
import com.carchargingalgo.model.CustomerHolder;
import com.carchargingalgo.utility.TimeManager;

public class Algorithm {
	
	public static ArrayList<CustomerHolder> selectedCustomers = new ArrayList<CustomerHolder>();
	private static ArrayList<CustomerHolder> mainSelectedCustomers = new ArrayList<CustomerHolder>();
	
	public static void loopThroughStationsFrom(int index) {
		selectedCustomers = new ArrayList<CustomerHolder>();
		for (int i = index, count = 0; count < Data.stationChargingPoint.size(); i++, count++) {
			if(i == Data.stationChargingPoint.size())
				i = 0;
			CustomerHolder ch = null;
			int p = 0;
			int [] time = TimeManager.getStartOfTheDayTime();
			do {
				ch = CustomerAssignmentAlgorithm.getAvailableCustomer(Data.stationChargingPoint.get(i).getStationType(), Data.stationChargingPoint.get(i).getStationId(), time);
				
				if(ch != null) {
					int end [] = TimeManager.getEndTime(ch.getRefillTime(), ch.getDuration());
					if(end[0] != -1) {
						selectedCustomers.add(ch);
						time = TimeManager.nextTime(TimeManager.getEndTime(ch.getRefillTime(), ch.getDuration()));
						p++;
					} else {
						ch = null;
					}
				}
				if(ch == null) {
					Data.stationChargingPoint.get(i).setNoOfCustomersHolding(p);
				}
			}while(ch != null);
		}
	}
	
	public static void run() {
		FilterAlgorithm.filterCustomer();
		loopThroughStationsFrom(0);
		mainSelectedCustomers = selectedCustomers;
		for (int i = 1; i < Data.stationChargingPoint.size(); i++) {
			loopThroughStationsFrom(i);
			if(selectedCustomers.size() > mainSelectedCustomers.size())
				mainSelectedCustomers = selectedCustomers;
		}
	}
	
	public static ArrayList<CustomerHolder> getCustomerHolderList(){
		return mainSelectedCustomers;
	}
	
}
