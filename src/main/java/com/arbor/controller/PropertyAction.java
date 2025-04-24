package com.arbor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import com.arbor.Beans.PropertyForm;
import com.arbor.Dao.propertyDao;
import com.arbor.model.Manager;
import com.arbor.model.property;

public class PropertyAction extends LookupDispatchAction  {

    @Override
    protected Map getKeyMethodMap() {
    	System.out.println("Inside propertyAction class");
    	
        Map<String, String> map = new HashMap<>();
      //  map.put("viewProperties", "viewProperties");
        map.put("addProperty", "addProperty");
     //   map.put("deleteProperty", "deleteProperty");
        map.put("updateProperty", "updateProperty");
      //  map.put("viewByArea", "viewByArea");
      //  map.put("lowestValue", "lowestValue");
      //  map.put("occupiedProperties", "occupiedProperties");
        
        System.out.println(map);
        return map;
    }
    
  


    public ActionForward addProperty(ActionMapping mapping, ActionForm form, 
                                     HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("Inside add property");
        return mapping.findForward("addProperty");
    }



    public ActionForward updateProperty(ActionMapping mapping, ActionForm form, 
                                        HttpServletRequest request, HttpServletResponse response)  throws Exception {
    	System.out.println("Inside Update property Method inside PropertyAction class ");
    	
    	// Retrieve the selected property based on ID
        PropertyForm propertyForm = (PropertyForm) form;
        propertyDao propertyDAO = new propertyDao();
        property property = propertyDAO.getPropertyById(propertyForm.getManagerRef());

        if (property != null) {
            // Set property details in request scope
        	 propertyForm.setId(property.getId());
             propertyForm.setName(property.getName());
             propertyForm.setArea(property.getArea());
             propertyForm.setRentalPrice(property.getRentalPrice());
             propertyForm.setCurrentValue(property.getCurrentValue());
             propertyForm.setOccupied(property.isOccupied() ? "Y" : "N");
             propertyForm.setManagerRef(property.getPropertyManagerRef());
             
             request.setAttribute("id", property.getId());
             request.setAttribute("propertyName",property.getName());
             request.setAttribute("propertyArea", property.getArea());   

             
             
             List<Manager> managersList = propertyDAO.getAllManagers(); 
             System.out.println("Manager list after retriving from database :"+managersList);
             
             HttpSession session = request.getSession();
        	 session.setAttribute("CurrentPropertyList", propertyForm);          
             
             
             if (managersList == null || managersList.isEmpty()) {
                 System.out.println(" No managers found! Check the database or DAO method.");
             } else {
            	 
            	
            	 session.setAttribute("managersList", managersList);
            	 
                 request.setAttribute("managersList", managersList);  
                  
                 
                 
                 System.out.println(" managersList set successfully in request scope.");
                 System.out.println("Verification after set req :"+request.getAttribute("managersList"));
                 System.out.println("Verification after set :"+request.getSession().getAttribute("managersList"));
             }
             
             
             request.setAttribute("managersList", managersList);
            
            
        } else {
            request.setAttribute("errorMessage", "Property not found.");
        }

        return mapping.findForward("updateProperty");
    }
    
	

}
