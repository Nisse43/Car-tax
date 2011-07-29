package com.dev43.cartax;

import java.io.Serializable;

public class Car implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private double lpkm;
	private int co;
	private int year;
	private int weight;
	private Boolean diesel;

	public Car(String name, Boolean isDiesel, int year, int weight, int co, double lpkm) {
		super();
		this.name = name;
		this.lpkm = lpkm;
		this.co = co;
		this.year = year;
		this.weight = weight;
		this.diesel = isDiesel;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getLpkm() {
		return lpkm;
	}
	
	public void setLpkm(double lpkm) {
		this.lpkm = lpkm;
	}
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getCo() {
		return co;
	}
	
	public void setCo(int co) {
		this.co = co;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}	
	
	public Boolean getDiesel() {
		return diesel;
	}

	public void setDiesel(Boolean diesel) {
		this.diesel = diesel;
	}
	
	@Override
	public String toString(){
		return name + " " + year;
	}
}
