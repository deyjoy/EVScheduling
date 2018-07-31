package com.sqacarcharging.CarCharging;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import com.carchargingalgo.algo.Algorithm;
import com.carchargingalgo.data.Data;
import com.carchargingalgo.excelreadwrite.ExcelFile;
import com.carchargingalgo.model.CustomerHolder;
import com.carchargingalgo.model.CustomerVehicle;
import com.carchargingalgo.model.Station;
import com.carchargingalgo.utility.TimeManager;
import com.carchargingalgo.view.GanttChartGenerator;

public class Main 
{
    public static void main( String[] args ) throws Exception
    {
    	
    	JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    	
		int returnValue = jfc.showOpenDialog(null);
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
		
			File selectedFile = jfc.getSelectedFile();
			Data.file_path = selectedFile.getAbsolutePath();
			
    		
			Station.generateStation();
            CustomerVehicle.generateVehicle();
            
            	ExcelFile.read();
    			Data.file_path = "";
            System.out.println("----------Start----------");
            Algorithm.run();
            System.out.println();
            System.out.println("Schedule:");
            for (int i = 0; i < Algorithm.getCustomerHolderList().size(); i++) {
    			CustomerHolder ch = Algorithm.getCustomerHolderList().get(i);
    			int [] start = ch.getRefillTime();
    			int [] end = TimeManager.getEndTime(start, ch.getDuration());
    			System.out.println("Customer " + ch.getCustomer().getCustomerId() 
    					+  "'s scheduled time: " + TimeManager.getTwoDigitTime(start[0]) + ":" + TimeManager.getTwoDigitTime(start[1]) 
    					+ " to " + TimeManager.getTwoDigitTime(end[0]) + ":" + TimeManager.getTwoDigitTime(end[1]) + " at Station " + ch.stationId 
    					);
    		}
            System.out.println();
            System.out.println("Total Customer scheduled: " + Algorithm.getCustomerHolderList().size());
            ExcelFile.write();
            GanttChartGenerator.run();
            System.out.println("----------End----------");
		}
   }
}
