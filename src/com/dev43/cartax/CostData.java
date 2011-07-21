package com.dev43.cartax;

import java.util.ArrayList;

import android.util.Log;

public class CostData {
	public static String[] DEMO_AXES_LABELS = { "1000 km", "Cost" };
	public static String DEMO_CHART_TITLE = "Car anual cost/km";

	public static double dieselPrice=1.378;
	public static double petrolPrice=1.587;

	public static double[] DEMO_X_AXIS_DATA = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20 };
	
	//Interval, the value is the max
	public static double[] oldCarsWeight = {0, 1300, 1400, 1500, 1600, 1700, 1800, 1900, 2000, 2100, 2200, 2300, 2400, 2500, 2600, 2700, 2800, 2900, 3000, 3100, 3200, 3300, 3400, 10000};
	public static double[] oldCarsFee = {75.92, 86.87, 98.55, 110.96, 124.10, 137.97, 152.57, 167.90, 183.96, 200.75, 218.27, 236.52, 255.50, 275.21, 295.65, 316.82, 338.72, 361.35, 384.71, 408.80, 433.62, 459.17, 485.45, 485.45};

	public static ArrayList<Car> CARS = new ArrayList<Car>(); 
	
	public static String[] CAR_TITLES = { "car1", "car2", "car3", "car4" };

	public static double[][] CAR_SERIES_LIST;
	
	public static void generateCarTitles(){
		CAR_TITLES = new String[CARS.size()];
		int i=0;
		for (Car car : CARS) {
			CAR_TITLES[i] = car.getName();
			i++;
		}
	}
	
	public static void generateCarSeriesList(){
		CAR_SERIES_LIST = new double[CARS.size()][]; 
		int i=0;
		for (Car car : CARS){
			CAR_SERIES_LIST[i] = calculateDrivingCost(car.getDiesel(), car.getYear(), car.getWeight(), car.getCo(), car.getLpkm());
			i++;
		}
	}

	static final String TAG = "CostData";
	
	public static double calculateNewCarsCost(int co2)
	{
		double newCarsCost;
		
		if(co2<=66)
		{
			newCarsCost = 19.345;
		}
		
		else if(co2>=400)
		{
			newCarsCost = 605.900;
		}
		else
		{

			newCarsCost = ((co2/100.0)*(8.1+(0.1*(co2-66))))*3.65;
		}	
		return newCarsCost;
	}
	
	public static double calculateOldCarsFee(int weight)
	{
		double oldCarsCost = 0;
		for (int i=0; oldCarsWeight[i]<=weight; i++){
			
			oldCarsCost=oldCarsFee[i];
		}
		return oldCarsCost;
	}
	
	
	public static double[] calculateDrivingCost(boolean isDiesel,int year, int weight, int co2, double lpkm)
	{
		double startCost=0;
		double additionalCost=0;
		double[] cost = new double[10];
		double kmPrice=0;

		
		if(isDiesel)
		{

			//Cars lighter than 2500 kg and registered before year 2001, gets cost-value from table
			if(weight<=2500&&year<2001)
			{
				startCost=calculateOldCarsFee(weight);
				additionalCost=Math.ceil(weight/100)*0.055*365;
				kmPrice=dieselPrice*lpkm;
			}
			else if(weight<=2500&&year>=2001)
			{
				startCost=calculateNewCarsCost(co2);
				additionalCost=Math.ceil(weight/100)*0.055*365;
				kmPrice=dieselPrice*lpkm;
			}
			else if(weight>2500&&year<2002)
			{
				startCost=calculateOldCarsFee(weight);
				additionalCost=Math.ceil(weight/100)*0.055*365;
				kmPrice=dieselPrice*lpkm;
			}
			else if(weight>2500&&year>=2002)
			{
				startCost=calculateNewCarsCost(co2);
				additionalCost=Math.ceil(weight/100)*0.055*365;
				kmPrice=dieselPrice*lpkm;
			}

		}
		else
		{	
			if(weight<=2500&&year<2001)
			{
				startCost=calculateOldCarsFee(weight);
				kmPrice=petrolPrice*lpkm;
			}
			else if(weight<=2500&&year>=2001)
			{
				startCost=calculateNewCarsCost(co2);
				kmPrice=petrolPrice*lpkm;
			}
			else if(weight>2500&&year<2002)
			{
				startCost=calculateOldCarsFee(weight);
				kmPrice=petrolPrice*lpkm;
			}
			else if(weight>2500&&year>=2002)
			{
				startCost=calculateNewCarsCost(co2);
				kmPrice=petrolPrice*lpkm;
			}
			
		}
		
		String test;
		test="hההההההr"+(startCost+additionalCost);
		
		Log.i(TAG, test);
		
		for (int i=0; i<10; i++){
			cost[i]=startCost+additionalCost+(i+1)*2000*kmPrice;
			
		}

		return cost;
	}
}
