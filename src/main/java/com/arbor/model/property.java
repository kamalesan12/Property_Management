package com.arbor.model;

public class property  implements java.io.Serializable{
	    private int id;
	    private int propertyManagerRef;
	    private String name;
	    private String area;
	    private double rentalPrice;
	    private double currentValue;
	    private boolean occupied;

	    public property() {}

	    
	    public property(int id, int propertyManagerRef, String name, String area, double rentalPrice, double currentValue, boolean occupied) {
	        this.id = id;
	        this.propertyManagerRef = propertyManagerRef;
	        this.name = name;
	        this.area = area;
	        this.rentalPrice = rentalPrice;
	        this.currentValue = currentValue;
	        this.occupied = occupied;
	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getPropertyManagerRef() {
			return propertyManagerRef;
		}

		public void setPropertyManagerRef(int propertyManagerRef) {
			this.propertyManagerRef = propertyManagerRef;
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


		public boolean isOccupied() {
			return occupied;
		}


		public void setOccupied(boolean occupied) {
			this.occupied = occupied;
		}





	

}
