package com.dev43.cartax;

/**
 * Copyright (C) <year> by <copyright holders>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 */

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
