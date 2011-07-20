package com.dev43.cartax;

import java.lang.reflect.Array;

public class CostData {
	public static String[] DEMO_AXES_LABELS = { "1000 km", "Cost" };
	public static String DEMO_CHART_TITLE = "Car anual cost/km";

	public static double dieselPrice=1.378;
	public static double petrolPrice=1.587;

	public static double[] DEMO_X_AXIS_DATA = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20 };


	public static String[] DEMO_TITLES = { "car1", "car2", "car3", "car4" };

	public static double[] DEMO_SERIES_1 = calculateDrivingCost(true, 2005, 1401, 118, 5.2/100);
	public static double[] DEMO_SERIES_2 = calculateDrivingCost(true, 2004, 1320, 134, 7.0/100);
	public static double[] DEMO_SERIES_3 = calculateDrivingCost(false, 2004, 1399, 140, 8.3/100);
	public static double[] DEMO_SERIES_4 = calculateDrivingCost(false, 2010, 1500, 130, 5.2/100);   
	
	public static double[][] DEMO_SERIES_LIST = {DEMO_SERIES_1, DEMO_SERIES_2, DEMO_SERIES_3, DEMO_SERIES_4};
	
	public static double[] calculateDrivingCost(boolean isDiesel,int year, int weight, int co2, double lpkm)
	{
		double startCost=0;
		double additionalCost=0;
		double[] cost = new double[10];
		double kmPrice=0;
		
		if(isDiesel)
		{
			startCost=70;
			additionalCost=Math.ceil(weight/100)*0.055*365;
			kmPrice=dieselPrice*lpkm;
			
		}
		else
		{
			startCost=70;
			kmPrice=petrolPrice*lpkm;
		}
		for (int i=0; i<10; i++){
			cost[i]=startCost+additionalCost+(i+1)*2000*kmPrice;
		}
		
		return cost;
	}
}
