package com.sqacarcharging.CarCharging;
import static org.junit.Assert.*;
import org.junit.Test;

import com.carchargingalgo.utility.TimeManager;

public class jUnitTest {
	
	@Test
	public void testGetMinuteDifferenceBetweenHours () {
		assertEquals(150, TimeManager.getMinuteDifferenceBetweenHours(8, 30, 11, 00));
	}
	
	@Test
	public void testGetEndTimeDataSet1() {
		int [] arry =  {10, 20};
		assertArrayEquals(arry, TimeManager.getEndTime(9, 30, 50));
	}

	@Test
	public void testGetEndTimeDateSet2() {
		int [] arry = {-1, -1};
		assertArrayEquals(arry, TimeManager.getEndTime(23, 30, 31));
	}
	
	@Test
	public void testGetHourFormatDataSet1() {
		int [] arry = {0, 50};
		assertArrayEquals(arry, TimeManager.getHourFormat(50));
	}
	
	@Test
	public void testGetHourFormatDataSet2() { 
		int [] arry = {2, 5};
		assertArrayEquals(arry, TimeManager.getHourFormat(125));
	}
			
}
