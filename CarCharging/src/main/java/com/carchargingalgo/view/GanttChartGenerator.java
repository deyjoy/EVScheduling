package com.carchargingalgo.view;

import java.awt.Color;
import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.gantt.XYTaskDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import com.carchargingalgo.algo.Algorithm;
import com.carchargingalgo.data.Data;
import com.carchargingalgo.utility.TimeManager;

public class GanttChartGenerator extends ApplicationFrame {

 private static final long serialVersionUID = 1L;

 public GanttChartGenerator(String s) throws ParseException {
     super(s);
     JPanel jpanel = createDemoPanel();
     jpanel.setPreferredSize(new Dimension(500, 300));
     setContentPane(jpanel);
 }

 private static JFreeChart createChart(IntervalXYDataset intervalxydataset) throws ParseException {
     JFreeChart jfreechart = ChartFactory.createXYBarChart("Joy's Car Charging Algorithm", "Resource", false, "Timing", intervalxydataset, PlotOrientation.HORIZONTAL, false, false, false);
     jfreechart.setBackgroundPaint(Color.white);
     XYPlot xyplot = (XYPlot) jfreechart.getPlot();
     xyplot.setRangePannable(true);
     ArrayList<String> aryStation = new ArrayList<String>();
     for (int i = 0; i < Algorithm.getCustomerHolderList().size(); i++) {
    	 aryStation.add("Station " + (Algorithm.getCustomerHolderList().get(i).stationId));
     }
     String[] stockArr = new String[aryStation.size()];
     stockArr = aryStation.toArray(stockArr);
     SymbolAxis symbolaxis = new SymbolAxis("Stations", stockArr);
     symbolaxis.setGridBandsVisible(false);
     xyplot.setDomainAxis(symbolaxis);
     XYBarRenderer xybarrenderer = (XYBarRenderer) xyplot.getRenderer();
     xybarrenderer.setUseYInterval(true);
     xyplot.setRangeAxis(new DateAxis("Timing"));
     ChartUtilities.applyCurrentTheme(jfreechart);
     return jfreechart;
 }

 public static JPanel createDemoPanel() throws ParseException {
     JFreeChart jfreechart = createChart(createDataset());
     ChartPanel chartpanel = new ChartPanel(jfreechart);
     chartpanel.setMouseWheelEnabled(true);
     return chartpanel;
 }

 private static IntervalXYDataset createDataset() throws ParseException {
     return new XYTaskDataset(createTasks());
 }

 private static TaskSeriesCollection createTasks() throws ParseException {
     TaskSeriesCollection taskseriescollection = new TaskSeriesCollection();
     ArrayList<TaskSeries> tasks = new ArrayList<TaskSeries>();
     for (int j = 0; j < Algorithm.getCustomerHolderList().size(); j++) {
		TaskSeries taskseries = new TaskSeries("Station " + Algorithm.getCustomerHolderList().get(j).stationId);
		for (int i = 0; i < Data.stationChargingPoint.size(); i++) {
			if(Data.stationChargingPoint.get(i).getStationId() == Algorithm.getCustomerHolderList().get(j).stationId){
				int start [] = Algorithm.getCustomerHolderList().get(j).getRefillTime();
    			int end [] = TimeManager.getEndTime(start, Algorithm.getCustomerHolderList().get(j).getDuration());
				Date startDate = new SimpleDateFormat("HH:mm").parse(TimeManager.getTwoDigitTime(start[0]) 
    					+ ":" + TimeManager.getTwoDigitTime(start[1]));
    			Date endDate = new SimpleDateFormat("HH:mm").parse(TimeManager.getTwoDigitTime(end[0]) 
    					+ ":" + TimeManager.getTwoDigitTime(end[1]));
				taskseries.add(new Task("C"+ Algorithm.getCustomerHolderList().get(i).getCustomer().getCustomerId(), 
						startDate, endDate));
			}
		}
		tasks.add(taskseries);
	}
     
     for (int i = 0; i < tasks.size(); i++) {
		taskseriescollection.add(tasks.get(i));
	}
     return taskseriescollection;
 }
 
 public static void run() throws ParseException{
	 GanttChartGenerator xytaskdatasetdemo1 = new GanttChartGenerator("JFreeChart : XYTaskDatasetDemo1.java");
     xytaskdatasetdemo1.pack();
     RefineryUtilities.centerFrameOnScreen(xytaskdatasetdemo1);
     xytaskdatasetdemo1.setVisible(true);
 }
}
