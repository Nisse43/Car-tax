package com.dev43.cartax;

public class Car {

	private String name;
	private double lpkm;
	private int co;
	private int year;
	public Car(String name, double lpkm, int co, int year) {
		super();
		this.name = name;
		this.lpkm = lpkm;
		this.co = co;
		this.year = year;
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
	
	@Override
	public String toString(){
		return name + " " + year;
	}
}
