package com.core.bean;

import java.io.Serializable;

public class CarArrange implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int carNo;
	private String passengers;
	private String driver;
	
	public int getCarNo() {
		return carNo;
	}
	public void setCarNo(int carNo) {
		this.carNo = carNo;
	}
	public String getPassengers() {
		return passengers;
	}
	public void setPassengers(String passengers) {
		this.passengers = passengers;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
}
