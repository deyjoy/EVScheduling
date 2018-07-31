package com.carchargingalgo.utility;

public class TimeManager {
	
	/**
	 * This method gives you the end time in hour and minute for a time duration from a start time.
	 * @param startTimeHour
	 * @param startTimeMinute
	 * @param durationInMinute
	 * @return
	 */
	public static int [] getEndTime(int startTimeHour, int startTimeMinute, int durationInMinute) {
		int hourAndMinute [] = {startTimeHour, startTimeMinute};
		for (int i = 0, j = startTimeMinute; i < durationInMinute; i++, j++) {
			if(hourAndMinute[0] == 24) {
				int [] fail = {-1, -1};
				return fail; 
			}
			if(j == 59) {
				hourAndMinute[0]++;
				hourAndMinute[1] = -1;
				j = -1;
			}
			hourAndMinute[1]++;
			if(hourAndMinute[0] == 24 && hourAndMinute[1] == 0) {
				int [] temp = {-1, -1};
				return temp;
			}
		}
		return hourAndMinute;
	}
	
	/**
	 * This method gives you the end time in hour and minute for a time duration from a start time.
	 * @param startTime
	 * @param durationInMinute
	 * @return
	 */
	public static int [] getEndTime(int [] startTime, int durationInMinute) {
		return getEndTime(startTime[0], startTime[1], durationInMinute);
	}

	/**
	 * This method gives the minute difference between two time.
	 * @param firstHour
	 * @param firstMinute
	 * @param secondHour
	 * @param secondMinute
	 * @return
	 */
	public static int getMinuteDifferenceBetweenHours(int firstHour, int firstMinute, int secondHour, int secondMinute) {
		int minute = 0;
		int i = firstHour;
		int j = firstMinute;
		while (i != secondHour || j != secondMinute) {
			if(j == 59) {
				i++;
				j = -1;
			}
			j++;
			minute++;
		}
		return minute;
	}
	
	/**
	 * This method gives the minute difference between two time.
	 * @param firstTime
	 * @param secondTime
	 * @return
	 */
	public static int getMinuteDifferenceBetweenHours(int [] firstTime, int [] secondTime) {
		return getMinuteDifferenceBetweenHours(firstTime[0], firstTime[1], secondTime[0], secondTime[1]);
	}

	/**
	 * This method gives in hour format as an array, hour is first index and minute being the second index.
	 * @param minute
	 * @return int []
	 */
	public static int [] getHourFormat(int minute) {
		int hourAndMinute [] = {0, 0};
		for (int i = 0, j = 0; i < minute; i++, j++) {
			if(hourAndMinute[0] == 24) {
				int [] fail = {-1, -1};
				return fail; 
			}
			if(j == 59) {
				hourAndMinute[0]++;
				hourAndMinute[1] = -1;
				
				j = -1;
			}
			hourAndMinute[1]++;
		}
		return hourAndMinute;
	}
	
	/**
	 * This method takes two integer as hour and minute and makes the time in an array, hour being he first index and minute being the second index.
	 * @param hour
	 * @param minute
	 * @return int []
	 */
	public static int [] getHourInArrayFormat(int hour, int minute) {
		int [] array = {hour, minute};
		return array;
	}
	
	/**
	 * This method gives you the next minute of the input time.
	 * @param time
	 * @return int array
	 */
	public static int [] nextTime(int [] time) {
		time[1]++;
		if(time[1] == 60) {
			time[0]++;
			time[1] = 0;
		}
		if(time[0] == 24 && time[1] == 0) {
			int [] temp = {-1, -1};
			return temp;
		}
		return time;
	}
	
	/**
	 * Takes two time and says if it is equal or not.
	 * @param firstTime
	 * @param secondTime
	 * @return
	 */
	public static boolean isEqualTime(int [] firstTime, int [] secondTime) {
		if(firstTime[0] == secondTime[0] && firstTime[1] == secondTime[1])
			return true;
		return false;
	}
	
	/**
	 * This method returns 00:00 that is the start time of the day.
	 * @return
	 */
	public static int [] getStartOfTheDayTime() {
		int [] startTime = {00, 00};
		return startTime;
	}
	
	/**
	 * This method returns 23:59 that is the end time of the day.
	 * @return
	 */
	public static int [] getEndOfTheDayTime() {
		int [] endTime = {23, 59};
		return endTime;
	}
	
	/**
	 * This method says if a specific time falls under a time slot or not.
	 * @param startTime
	 * @param endTime
	 * @param time
	 * @return
	 */
	public static boolean isTimeInTimeSlot(int [] startTime, int [] endTime, int [] time) {
		if(startTime[0] == time[0] && endTime[0] >= time[0]) {
			if(startTime[0] <= time[0] && startTime[1] > time[1] )
				return false;
			else if(endTime[0] == time[0] && endTime[1] < time[1])
				return false;
			else
				return true;
		}
		return false;
	}
	
	public static String getTwoDigitTime(int time){
		String twoDigitTime = "" + time;
		if(time < 10){
			twoDigitTime = "" + 0 + twoDigitTime;
		}
		return twoDigitTime;
	}
	
	/**
	 * This method says if a time is less than a given time.
	 * @param time
	 * @param givenTime
	 * @return
	 */
	public static boolean isTimeLessThanGivenTime(int [] time, int [] givenTime) {
		if(time[0] <= givenTime[0]) {
			if(time [0] == givenTime[0] && time[1] > givenTime[1])
				return false;
			return true;
		}
		return false;
	}
	
	/**
	 * This method says if a time duration exceed or does not exceed the time slot. 
	 * @param startTime
	 * @param endTime
	 * @param duration
	 * @return
	 */
	public static boolean isDurationStayWithInTimeSlot(int [] startTime, int [] endTime, int duration) {
		int [] afterDurationTime = getEndTime(startTime, duration);
		return isTimeLessThanGivenTime(afterDurationTime, endTime);
	}
	
	/**
	 * This is a main method of this class to test this class.
	 * @param args
	 */
	public static void main(String [] args) {
		int [] i = TimeManager.getEndTime(9, 30, 50);
		System.out.println(i[0] + ":" + i[1]);
		
		int [] j = TimeManager.getEndTime(23, 30, 31);
		System.out.println(j[0] + ":" + j[1]);
		
		int min = getMinuteDifferenceBetweenHours(8, 30, 11, 00);
		System.err.println(min);
		
		int ham [] = TimeManager.getHourFormat(50);
		System.out.println(ham[0]+ ":" + ham[1]);
		
		ham  = TimeManager.getHourFormat(125);
		System.out.println(ham[0]+ ":" + ham[1]);
		
		ham  = TimeManager.getHourFormat(1441);
		System.out.println(ham[0]+ ":" + ham[1]);
		int [] time = getStartOfTheDayTime();
		do {
			System.out.println(time[0] + ":" + time[1]);
			time = nextTime(time);
		} while (time[0] != -1);
		int start [] = {23, 20};
		int end [] = {23, 50};
		System.out.println(isDurationStayWithInTimeSlot(start, end, 35));
	}
	
}
