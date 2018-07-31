package com.carchargingalgo.algo;

import com.carchargingalgo.data.Data;
import com.carchargingalgo.utility.TimeManager;

public class FilterAlgorithm {

	public static void filterCustomer() {
		for (int i = 0; i < Data.customer.size(); i++) {
			boolean isPossible = false;
			if(!Data.customer.get(i).isNullValue && !TimeManager.isEqualTime(Data.customer.get(i).getPreferredStartTime(), Data.customer.get(i).getPreferredEndTime()) 
					&&  Data.customer.get(i).getMileTotravel() > 0
					&& TimeManager.isTimeLessThanGivenTime(Data.customer.get(i).getPreferredStartTime(), Data.customer.get(i).getPreferredEndTime())) {
				for (int j = 0; j < Data.typesOfStation.size(); j++) {
					if(Data.customer.get(i).getTimeRequiredToRefilleInStationAt(j) != -1) {
						if(Data.customer.get(i).getTimeRequiredToRefilleInStationAt(j) <= 
								TimeManager.getMinuteDifferenceBetweenHours(Data.customer.get(i).getPreferredStartTime(), 
										Data.customer.get(i).getPreferredEndTime())) {
							isPossible = true;
							Data.customer.get(i).setPossibility(isPossible);
							break;
						}
					}
				}
			}
		}
	}
	
}
