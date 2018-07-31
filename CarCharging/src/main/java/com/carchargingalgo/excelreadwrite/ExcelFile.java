package com.carchargingalgo.excelreadwrite;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.carchargingalgo.algo.Algorithm;
import com.carchargingalgo.data.Data;
import com.carchargingalgo.model.CustomerHolder;
import com.carchargingalgo.model.CustomerVehicle;
import com.carchargingalgo.model.GasStation;
import com.carchargingalgo.utility.TimeManager;

public class ExcelFile {
	private File file;

	public ExcelFile(File file){
	  this.file = file;
	 }
	
	public static void read() throws IOException {

	 File excelFile = new File(Data.file_path); 
	 ExcelFile uploaded = new ExcelFile(excelFile);
	
	 uploaded.extractAsList();
	} 
	 
	 public ArrayList<ArrayList<Object>> extractAsList(){
	  
	  ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
	  int maxDataCount =0;
	  int maxDataCount2 =0;
	  try{
	       FileInputStream file = new FileInputStream(this.file);
	      
	      // Create Workbook instance holding reference to .xlsx file
	      XSSFWorkbook workbook = new XSSFWorkbook(file);

	      // Get first/desired sheet from the workbook
	      XSSFSheet sheet = workbook.getSheetAt(0);
	      
	      // Iterate through each rows one by one
	      Iterator<Row> rowIterator = sheet.iterator();
	      
	      while (rowIterator.hasNext()) {
	          
	    	  Row row = rowIterator.next();
	          
	          //Skip the first row because it will be header
	          if (row.getRowNum() == 0) {
	              maxDataCount = row.getLastCellNum();
	              continue;
	          }
	    
		      //if row is empty then break the loop,do not go further 
		      if(this.isRowEmpty(row,maxDataCount)){
		       //Exit the processing
		      break;
		      }
	    
		      ArrayList<String> singleRows = new ArrayList<String>();
	    
		     // For each row, iterate through all the columns
		     for(int cn=0; cn<maxDataCount; cn++) {
	
		        Cell cell = row.getCell(cn);
				
		        if(DateUtil.isCellDateFormatted(cell) && cell.getDateCellValue() != null){
	         		singleRows.add("" + new SimpleDateFormat("HH").format(cell.getDateCellValue()));
	         		singleRows.add("" + new SimpleDateFormat("mm").format(cell.getDateCellValue()));
	            }else {
	            	 if (cell.getNumericCellValue() != 0)
	             	singleRows.add("" + new java.text.DecimalFormat("0").format( cell.getNumericCellValue() ));
	            }
		     }
		     
		     try {
		    		 int id = Integer.parseInt(singleRows.get(0));
				     int start [] = {Integer.parseInt(singleRows.get(1)), Integer.parseInt(singleRows.get(2))};
				     int end [] = {Integer.parseInt(singleRows.get(3)), Integer.parseInt(singleRows.get(4))};
				     int mile = Integer.parseInt(singleRows.get(5));
				     int eVType = Integer.parseInt(singleRows.get(6));
				     Data.customer.add(new CustomerVehicle(id, start, end, mile, eVType));   
				} catch (Exception e) {
					Data.customer.add(new CustomerVehicle(true)); 
				}
		     
		 }
	   
	      //****************** SHEET 2
	      //Get first/desired sheet from the workbook
	      XSSFSheet sheet2 = workbook.getSheetAt(1);
	      
	      // Iterate through each rows one by one
	      Iterator<Row> rowIterator2 = sheet2.iterator();
	      
	      while (rowIterator2.hasNext()) {	  
	    	  
	        Row row2 = rowIterator2.next();
	        
	        //Skip the first row beacause it will be header
	        if (row2.getRowNum() == 0) {
	            maxDataCount2 = row2.getLastCellNum();
	            continue;
	        }
		    
	        //if row is empty then break the loop,do not go further 
		    if(this.isRowEmpty(row2,maxDataCount2)){
		     //Exit the processing
		     break;
		    }
	    
		    ArrayList<String> singleRows2 = new ArrayList<String>();
		    
		    // For each row, iterate through all the columns
		    for(int cn2=0; cn2<maxDataCount2; cn2++) {
		        Cell cell2 = row2.getCell(cn2);
				singleRows2.add("" + new java.text.DecimalFormat("0").format( cell2.getNumericCellValue() ));
		     }
		     int id = Integer.parseInt(singleRows2.get(0));
		     int stationType = Integer.parseInt(singleRows2.get(1));
		     Data.stationChargingPoint.add(new GasStation(id, stationType));
	     
	   }
	   file.close();
	   workbook.close();   
	  } catch (Exception e) {  e.printStackTrace();}
	  
	  return list;
	 }
 
	 @SuppressWarnings("deprecation")
	public boolean isRowEmpty(Row row,int lastcellno) {
	     for (int c = row.getFirstCellNum(); c < lastcellno; c++) {
	         Cell cell = row.getCell(c);
	         if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
	             return false;
	     }
	     return true;
	 }
	 
	 /**
	  * From here.
	  */
	 
	// private static final String LINK_PATH = "ExcelFile/Output.xlsx";
		public static String getThisrow() {
			return thisrow;
		}

		public static void setThisrow(String thisrow) {
			ExcelFile.thisrow = thisrow;
		}

		public static String getRowcolumn1() {
			return rowcolumn1;
		}

		public static void setRowcolumn1(String rowcolumn1) {
			ExcelFile.rowcolumn1 = rowcolumn1;
		}

		public static String getRowcolumn2() {
			return rowcolumn2;
		}

		public static void setRowcolumn2(String rowcolumn2) {
			ExcelFile.rowcolumn2 = rowcolumn2;
		}

		public static String getRowcolumn3() {
			return rowcolumn3;
		}

		public static void setRowcolumn3(String rowcolumn3) {
			ExcelFile.rowcolumn3 = rowcolumn3;
		}

		public static String getRowcolumn4() {
			return rowcolumn4;
		}

		public static void setRowcolumn4(String rowcolumn4) {
			ExcelFile.rowcolumn4 = rowcolumn4;
		}

		public static String getRowcolumn5() {
			return rowcolumn5;
		}

		public static void setRowcolumn5(String rowcolumn5) {
			ExcelFile.rowcolumn5 = rowcolumn5;
		}

		public static String getRowcolumn6() {
			return rowcolumn6;
		}

		public static void setRowcolumn6(String rowcolumn6) {
			ExcelFile.rowcolumn6 = rowcolumn6;
		}

		public static String getRowcolumn7() {
			return rowcolumn7;
		}

		public static void setRowcolumn7(String rowcolumn7) {
			ExcelFile.rowcolumn7 = rowcolumn7;
		}

		public static String getRowcolumn8() {
			return rowcolumn8;
		}

		public static void setRowcolumn8(String rowcolumn8) {
			ExcelFile.rowcolumn8 = rowcolumn8;
		}

		public static int getRownumber() {
			return rownumber;
		}

		public static void setRownumber(int rownumber) {
			ExcelFile.rownumber = rownumber;
		}

		private static String thisrow;
		private static String rowcolumn1;
		private static String rowcolumn2;
		private static String rowcolumn3;
		private static String rowcolumn4;
		private static String rowcolumn5;
		private static String rowcolumn6;
		private static String rowcolumn7;
		private static String rowcolumn8;
		private static int rownumber;
	 
	 
	 public static void write() throws IOException
	 
	 {
	     //String destinationFilePath = link;
	     
	     // Create object of FileOutputStream
	     FileOutputStream fis = new FileOutputStream(Data.LINK_PATH);
	     
	     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	  
	     // Build the Excel File
	     XSSFWorkbook workbook = new XSSFWorkbook();
	     
	     //Create the spreadsheet
	     XSSFSheet spreadSheet = workbook.createSheet("sheet1");
	     
	     
			//Create First Row
			XSSFRow row1 = spreadSheet.createRow(0);
			XSSFCell r1c1 = row1.createCell(0);
			r1c1.setCellValue("No. of Reservations");
			XSSFCell r1c2 = row1.createCell(1);
			r1c2.setCellValue("Customer Id");
			XSSFCell r1c3 = row1.createCell(2);
			r1c3.setCellValue("EV Tye");
			XSSFCell r1c4 = row1.createCell(3);
			r1c4.setCellValue("Start Time");		
			XSSFCell r1c5 = row1.createCell(4);
			r1c5.setCellValue("End Time");
			XSSFCell r1c6 = row1.createCell(5);
			r1c6.setCellValue("Duration");
			XSSFCell r1c7 = row1.createCell(6);
			r1c7.setCellValue("Station No.");
			XSSFCell r1c8 = row1.createCell(7);
			r1c8.setCellValue("Station Type");
			
	     for (int i = 0; i < Algorithm.getCustomerHolderList().size(); i++) {
				CustomerHolder ch = Algorithm.getCustomerHolderList().get(i);
				int [] start = ch.getRefillTime();
				int [] end = TimeManager.getEndTime(start, ch.getDuration());//ch.getFinishTime();
				
				rownumber = i + 1;
				//Create Second Row
				thisrow = "row"+rownumber;
				
				rowcolumn1 = "r"+rownumber+"c1";
				rowcolumn2 = "r"+rownumber+"c2";
				rowcolumn3 = "r"+rownumber+"c3";
				rowcolumn4 = "r"+rownumber+"c4";
				rowcolumn5 = "r"+rownumber+"c5";
				rowcolumn6 = "r"+rownumber+"c6";
				rowcolumn7 = "r"+rownumber+"c7";
				rowcolumn8 = "r"+rownumber+"c8";
				
				XSSFRow thisrow = spreadSheet.createRow(rownumber);
				XSSFCell rowcolumn1 = thisrow.createCell(0);
				rowcolumn1.setCellValue(rownumber);
				XSSFCell rowcolumn2 = thisrow.createCell(1);
				rowcolumn2.setCellValue(ch.getCustomer().getCustomerId());
				XSSFCell rowcolumn3 = thisrow.createCell(2);
				rowcolumn3.setCellValue(ch.getCustomer().getActualEVType());
				XSSFCell rowcolumn4 = thisrow.createCell(3);
				rowcolumn4.setCellValue(start[0] + ":" + start[1]);		
				XSSFCell rowcolumn5 = thisrow.createCell(4);
				rowcolumn5.setCellValue(end[0] + ":" + end[1]);
				
				XSSFCell rowcolumn6 = thisrow.createCell(5);
				rowcolumn6.setCellValue(ch.getDuration());
				
				XSSFCell rowcolumn7 = thisrow.createCell(6);
				rowcolumn7.setCellValue(ch.stationId);
				XSSFCell rowcolumn8 = thisrow.createCell(7);
				rowcolumn8.setCellValue(ch.getActualStationType());
				
			}
	     
			fis.close();
			FileOutputStream fos = new FileOutputStream(new File(Data.LINK_PATH));
		        workbook.write(fos);
		        fos.close();
			outputStream.close();
			workbook.close();
	 }
	 
}
