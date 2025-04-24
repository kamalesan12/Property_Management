package com.arbor.model;

public class Manager {
	
	private int id;
    private String name;
    private int managerRef;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	   
	public int getManagerRef() {
		return managerRef;
	}


	public void setManagerRef(int managerRef) {
		this.managerRef = managerRef;
	}
	
	


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public Manager() {}

    // Parameterized Constructor
    public Manager(int id, String name) {
        this.id = id;
        this.name = name;
    }

	@Override
	public String toString() {
		return "Manager [id=" + id + ", name=" + name + "]";
	}
}
