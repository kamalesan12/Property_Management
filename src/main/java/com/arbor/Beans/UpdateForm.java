package com.arbor.Beans;

import org.apache.struts.action.ActionForm;

public class UpdateForm extends ActionForm {
	
	private int id;
    private int managerRef;
    private String name; 
    private String area;
    private double rentalPrice;
    private double currentValue;
    private String occupied;
    private String action;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getManagerRef() {
		return managerRef;
	}
	public void setManagerRef(int managerRef) {
		this.managerRef = managerRef;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public double getRentalPrice() {
		return rentalPrice;
	}
	public void setRentalPrice(double rentalPrice) {
		this.rentalPrice = rentalPrice;
	}
	public double getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}
	public String getOccupied() {
		return occupied;
	}
	public void setOccupied(String occupied) {
		this.occupied = occupied;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
    
    
	

}
