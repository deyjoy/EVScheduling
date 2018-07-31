package com.carchargingalgo.algo;

import java.util.ArrayList;

import com.carchargingalgo.data.Data;
import com.carchargingalgo.model.CustomerHolder;
import com.carchargingalgo.model.CustomerVehicle;
import com.carchargingalgo.utility.TimeManager;

public class CustomerAssignmentAlgorithm {

	public static CustomerHolder getAvailableCustomer(int stationType, int stationId, int [] time) {
		ArrayList<CustomerHolder> selectedCustomerHolder = new ArrayList<CustomerHolder>();
		boolean [] isSelected = new boolean[Data.customer.size()];
		for (int i = 0; i < isSelected.length; i++) {
			isSelected[i] = false;
		}
		do {
			for (int i = 0; i < Data.customer.size(); i++) {
				if(!isSelected[i] && Data.customer.get(i).getTimeRequiredToRefilleInStationAt(stationType) != -1 
						&& !checkIsCustomerBooked(Data.customer.get(i)) && Data.customer.get(i).isPossibility() 
						&& TimeManager.isTimeInTimeSlot(Data.customer.get(i).getPreferredStartTime(), 
						Data.customer.get(i).getPreferredEndTime(), time) 
						&& TimeManager.isDurationStayWithInTimeSlot(time, Data.customer.get(i).getPreferredEndTime(), 
								Data.customer.get(i).getTimeRequiredToRefilleInStationAt(stationType))) {
					int duration = Data.customer.get(i).getTimeRequiredToRefilleInStationAt(stationType);
					int [] endT = TimeManager.getEndTime(time, duration);
					int [] startT = time;
					CustomerHolder cholder = new CustomerHolder(Data.customer.get(i), startT, duration, i, stationType, stationId);
					selectedCustomerHolder.add(cholder);
					cholder = selectedCustomerHolder.get(selectedCustomerHolder.size()-1);
					startT = cholder.getRefillTime();
					isSelected[i] = true;
					time = endT;
				}
			}
			time = TimeManager.nextTime(time);
		} while(time[0] != -1);

		if (selectedCustomerHolder.size() == 0) {
			return null;
		} else if (selectedCustomerHolder.size() == 1) {
			return selectedCustomerHolder.get(0);
		} else {
			int index = 0;
			int minStartTime [] = selectedCustomerHolder.get(0).getRefillTime();
			for (int j = 1; j < selectedCustomerHolder.size(); j++) {
				if(TimeManager.isTimeLessThanGivenTime(selectedCustomerHolder.get(j).getRefillTime(), minStartTime)) {
					index = j;
					minStartTime = selectedCustomerHolder.get(j).getRefillTime();
				}
			}
			return selectedCustomerHolder.get(index);
		}
	}
	
	public static boolean checkIsCustomerBooked(CustomerVehicle cv) {
		for (int i = 0; i < Algorithm.selectedCustomers.size(); i++) {
			if(Algorithm.selectedCustomers.get(i).getCustomer().equals(cv)) 
				return true;
		}
		return false;
	}
	
}
