package com.arbor.Dao;


import com.arbor.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class propertyDao {
	

    

    private static final String URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";
    private static final String USERNAME = "ARBOR_GOLD";
    private static final String PASSWORD = "arbor_gold";
    
    
    
    
    

    // Retrieves all properties data irrespective of users
    public List<property> getAllProperties() throws Exception {
    	
    	
    	System.out.println("Inside get all properties method");
        List<property> list = new ArrayList();
        String query = "SELECT * FROM PROPERTY";
        
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement();
        		
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new property(
                    rs.getInt("ID"),
                    rs.getInt("PROPERTY_MANAGER_ID"),
                    rs.getString("NAME"),
                    rs.getString("AREA"),
                    rs.getDouble("RENTAL_PRICE"),
                    rs.getDouble("CURRENT_VALUE"),
                    rs.getString("OCCUPIED").equals("Y")
                ));
            }
        }
        return list;
    }

    // Save property
    public boolean addProperty(property property) throws Exception {
    	System.out.println("Inside a add property Dao method");
        String query = "INSERT INTO PROPERTY (ID, PROPERTY_MANAGER_ID, NAME, AREA, RENTAL_PRICE, CURRENT_VALUE, OCCUPIED) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            ps.setInt(1, property.getId());
            ps.setInt(2, property.getPropertyManagerRef());
            ps.setString(3, property.getName());
            ps.setString(4, property.getArea());
            ps.setDouble(5, property.getRentalPrice());
            ps.setDouble(6, property.getCurrentValue());
            ps.setString(7, property.isOccupied() ? "Y" : "N");

            return ps.executeUpdate() >0;
        }
    }
    
    
    //GetPropertyByID
    
    public property getPropertyById(int id) throws Exception {
        property property = null;
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM PROPERTY WHERE PROPERTY_MANAGER_ID = ?")) {
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                property = new property();
                property.setId(rs.getInt("ID"));
                property.setPropertyManagerRef(rs.getInt("PROPERTY_MANAGER_ID"));
                property.setName(rs.getString("NAME"));
                property.setArea(rs.getString("AREA"));
                property.setRentalPrice(rs.getDouble("RENTAL_PRICE"));
                property.setCurrentValue(rs.getDouble("CURRENT_VALUE"));
                property.setOccupied(rs.getString("OCCUPIED").equals("Y"));
            }
        }
        
        return property;
    }
    
    
    //GetPropertyByproperty Id
    
    public property getPropertyBypropId(int id) throws Exception {
        property property = null;
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM PROPERTY WHERE ID = ?")) {
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                property = new property();
                property.setId(rs.getInt("ID"));
                property.setPropertyManagerRef(rs.getInt("PROPERTY_MANAGER_ID"));
                property.setName(rs.getString("NAME"));
                property.setArea(rs.getString("AREA"));
                property.setRentalPrice(rs.getDouble("RENTAL_PRICE"));
                property.setCurrentValue(rs.getDouble("CURRENT_VALUE"));
                property.setOccupied(rs.getString("OCCUPIED").equals("Y"));
            }
        }
        
        return property;
    }
    
    
    //GetMultipropertiesById
    public List<property> getPropertiesById(int id) throws Exception {
        List<property> properties = new ArrayList<>();
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM PROPERTY WHERE PROPERTY_MANAGER_ID = ?")) {
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
            	property prop=new property();
            	prop = new property();
            	prop.setId(rs.getInt("ID"));
            	prop.setPropertyManagerRef(rs.getInt("PROPERTY_MANAGER_ID"));
            	prop.setName(rs.getString("NAME"));
            	prop.setArea(rs.getString("AREA"));
            	prop.setRentalPrice(rs.getDouble("RENTAL_PRICE"));
            	prop.setCurrentValue(rs.getDouble("CURRENT_VALUE"));
            	prop.setOccupied(rs.getString("OCCUPIED").equals("Y"));
            	
            	properties.add(prop);
            }
            
            System.out.println("Inside getpropertiesById method DAo :"+properties);
        }
        
        return properties;
    }
    
    public int getPropertyUserPass(String username,String password) throws Exception {
    	int propertyManagerId = -1;
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = conn.prepareStatement("SELECT ID FROM propertymanager WHERE  name=? and password=?")) {
            
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
            	propertyManagerId=rs.getInt("ID");
            }
        } 
        
        System.out.println("Inside getPropertyUserPass method :"+propertyManagerId);

        return propertyManagerId;
    }

    
    
    //ManagersList
    
    public static List<Manager> getAllManagers() throws Exception {
        List<Manager> managers = new ArrayList();
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM PROPERTYMANAGER")) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Manager manager = new Manager();
                manager.setId(rs.getInt("ID"));
                manager.setName(rs.getString("NAME"));
                managers.add(manager);
            }
        }
        return managers;
    }


    // Update property
    public boolean updateProperty(int id, String name,String area,double rentalPrice,double currentValue,String occupied,int managerRef ) throws Exception {
   	
        String query = "UPDATE PROPERTY SET RENTAL_PRICE=?, CURRENT_VALUE=?, OCCUPIED=? WHERE ID=?";
 
        
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            ps.setDouble(1, rentalPrice);
            ps.setDouble(2, currentValue);
            ps.setString(3, occupied.equalsIgnoreCase("Y") ? "Y" : "N");
            ps.setInt(4, id);

            return ps.executeUpdate()>0;
        }
    }

    // Delete property
    public HashMap<String,Object> deleteProperty(int id) throws Exception {
    	property delproperty=new property();
    	delproperty=getPropertyBypropId(id);
    	
    	boolean isDeleted=false;
    	
    	
        String query = "DELETE FROM PROPERTY WHERE ID=?";
        
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            ps.setInt(1, id);
            
            
             isDeleted=ps.executeUpdate()>0;
            
        }
        
        HashMap<String,Object> hasM=new HashMap<String,Object>();
        
        hasM.put("isDeleted", isDeleted);
        hasM.put("deletedProp", delproperty);
        
        return hasM;
    }
    
    
    //SearchByArea
    
    public List<property> getPropertiesByArea(String area) {
    	List<property> filteredList = new ArrayList<>();// Fetch all properties
    	
    	try {
            List<property> properties = getAllProperties(); // Fetch all properties
            
            for (property p : properties) {
                if (p.getArea().equalsIgnoreCase(area)) {
                    filteredList.add(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the error
        }
        return filteredList;
    }
    
    
    //Viewing occupied properties
    
    public List<property> getOccupiedProperties(int id) throws Exception {
        List<property> occupiedList = new ArrayList<>();

        int managerId = id;
        String query = "SELECT * FROM property WHERE occupied = 'Y' AND property_manager_id=?";

        Class.forName("oracle.jdbc.driver.OracleDriver");

        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, managerId); 

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    property prop = new property();
                    prop.setId(rs.getInt("ID"));
                    prop.setPropertyManagerRef(rs.getInt("PROPERTY_MANAGER_ID"));
                    prop.setName(rs.getString("NAME"));
                    prop.setArea(rs.getString("AREA"));
                    prop.setRentalPrice(rs.getDouble("RENTAL_PRICE"));
                    prop.setCurrentValue(rs.getDouble("CURRENT_VALUE"));
                    prop.setOccupied("Y".equalsIgnoreCase(rs.getString("OCCUPIED")));

                    occupiedList.add(prop);
                }
            }
        }

        return occupiedList;
    }
    
    
    // Update property
    public boolean updatePropertyDetails(property prop) throws Exception {
   	
        String query = "UPDATE PROPERTY SET RENTAL_PRICE=?, CURRENT_VALUE=?, OCCUPIED=?, PROPERTY_MANAGER_ID=?, AREA=? WHERE ID=?";
 
        
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            ps.setDouble(1, prop.getRentalPrice());
            ps.setDouble(2, prop.getCurrentValue());
            ps.setString(3, prop.isOccupied() ? "Y":"N");
          //  String checkstatus=prop.isOccupied() ? "Y":"N";
            System.out.println("updated property occupied status :" );
            ps.setInt(4, prop.getPropertyManagerRef());
            ps.setString(5, prop.getArea());
            ps.setInt(6, prop.getId());

            return ps.executeUpdate()>0;
        }
    }
		
	

}
