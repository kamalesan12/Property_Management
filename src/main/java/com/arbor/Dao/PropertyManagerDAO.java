package com.arbor.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertyManagerDAO {


	public boolean validateUser(String name, String password) throws Exception {
		boolean isValid = false;
		String sql = "SELECT COUNT(*) FROM PROPERTYMANAGER WHERE NAME = ? AND PASSWORD = ?";

		try (Connection conn = DBConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, name);
			pstmt.setString(2, password);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next() && rs.getInt(1) > 0) {
					isValid = true;
				}
			}
		} catch (SQLException e) {
			throw new Exception("Database error while validating user", e);
		}
		return isValid;
	}
      
      public boolean checkIfUserExists(String name) throws Exception {
          String sql = "SELECT id FROM propertymanager WHERE name = ?";
          

          try (Connection conn = DBConnection.getConnection();
               PreparedStatement pstmt = conn.prepareStatement(sql)) {

              pstmt.setString(1, name);

              try (ResultSet rs = pstmt.executeQuery()) {
                  return rs.next(); // Returns true if a record exists
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          return false;
      }

       public int getPropertyManagerId(String name, String password) {
           int propertyManagerId = -1;
           String sql = "SELECT id FROM propertymanager WHERE name = ? AND password = ?";

           try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

               pstmt.setString(1, name);
               pstmt.setString(2, password);

               try (ResultSet rs = pstmt.executeQuery()) {
                   if (rs.next()) {
                       propertyManagerId = rs.getInt("id");
                   }
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
           return propertyManagerId;
       }
       


       public boolean insertNewUser(String name, String password) {
           String sql = "INSERT INTO propertymanager (name, password) VALUES (?, ?)";
           
           try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

               ps.setString(1, name);
               ps.setString(2, password);

               int rowsAffected = ps.executeUpdate();
               return rowsAffected > 0; // returns true if rows inserted greater than 0
           } catch (Exception e) {
               e.printStackTrace();
           }
           return false;
       }
       
       
   }