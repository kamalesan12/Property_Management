package com.arbor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.arbor.Beans.PropertyForm;
import com.arbor.Dao.propertyDao;
import com.arbor.model.Manager;
import com.arbor.model.property;

	

public class PropertyProcessingAction extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        
        String action = request.getParameter("action");
        System.out.println("Action received: " + action);
        
        if (action == null || action.trim().isEmpty()) {
        	action = "viewAll";
        	
        	System.out.println("Before Login loading viewAll content with : "+action +" Action");
        }
        
        PropertyForm propertyForm = (PropertyForm) form;
        propertyDao dao = new propertyDao();
        boolean success = false;
        
        if(true) {
        	System.out.println("Yes i came inside PropertyProcessingAction class ");
        }
        
        
        switch (action) {
        
        
        case "viewAll":
        	System.out.println("Executing viewAll...");
            System.out.println("Loading all available properties...");
            List<property> allProperties = dao.getAllProperties();
            
            System.out.println("Properies Details loaded from the databse :"+allProperties);

            if (allProperties == null) {
                System.out.println("CAUTION : No properties retrieved from database!!");
                return mapping.findForward("failure");  // error.jsp
            }	

            request.getSession().setAttribute("AvailableProperties", allProperties);
            System.out.println("Properties loaded successfully, forwarding to index.jsp");
            
            return mapping.findForward("indexPage");  
        
     
            case "addProperty":
                System.out.println("Inside addProperty");
                property newProperty = new property();
                
                newProperty.setId(propertyForm.getId());
                newProperty.setPropertyManagerRef((int)request.getSession().getAttribute("propManagerId"));
                newProperty.setName(propertyForm.getName());
                newProperty.setArea(propertyForm.getArea());
                newProperty.setRentalPrice(propertyForm.getRentalPrice());
                newProperty.setCurrentValue(propertyForm.getCurrentValue());
                newProperty.setOccupied("Y".equalsIgnoreCase(propertyForm.getOccupied()));
                
                success = dao.addProperty(newProperty);
                System.out.println("After adding property successfully: " + success);
                if(success) {
           			List<property> properties = dao.getPropertiesById((int)request.getSession().getAttribute("propManagerId"));
     	            if (properties == null) {
     	                properties = new ArrayList<>(); 
     	            }
     	            System.out.println("Properties deleted against user id :"+(int) request.getSession().getAttribute("propManagerId"));

     	            request.getSession().setAttribute("Currentproperties", properties);
     	            System.out.println("Session attribute set: " + request.getSession().getAttribute("Currentproperties"));
     	            
     	           request.setAttribute("addpropsuccess", "Details Added Successfully !!");
                }
                break;

            case "updateProperty":
                System.out.println("Inside updateProperty");
                
                int selectedId=propertyForm.getSelectedProperty();
                System.out.println("Selected property for Updating");
                
                property property=new property();
                property=dao.getPropertyBypropId(selectedId);
                
                List<Manager> managersList = propertyDao.getAllManagers(); 
                System.out.println("Manager list after retriving from database :"+managersList);
                
                if (managersList == null || managersList.isEmpty()) {
                    System.out.println(" No managers found! Check the database or DAO method.");
                } else {
                	System.out.println(managersList);
                	request.getSession().setAttribute("managersList", managersList);
                	
                }
                
                request.getSession().setAttribute("updateReqDetails", property);
                
                if(property==null) {
                	
                    request.setAttribute("currentAction", "updateProperty");
                    request.setAttribute("message", success ? "Action successful !!" : "Action failed !!");
                    System.out.println("Redirecting to: " + (success ? "actionSuccess" : "actionFailure"));
                	
                	return mapping.findForward("actionfailure");
                }
                
                return mapping.findForward("updateproperty");
                
            case "deleteProperty":
                System.out.println("Inside deleteProperty");
                System.out.println("Selected property details to delete");
                              
                System.out.println(" Property ID: " + propertyForm.getSelectedProperty());               
                
                int propertyId = propertyForm.getSelectedProperty();
                HashMap<String,Object> result=dao.deleteProperty(propertyId);
                
                success=(boolean) result.get("isDeleted");
                property prop=(property) result.get("deletedProp");
                
                
                if(success) {
                	System.out.println("property Deleted Succesfully : "+propertyId);
                	System.out.println("Deleted property Deatils :"+prop.getId());
                	
       			List<property> properties = dao.getPropertiesById((int)request.getSession().getAttribute("propManagerId"));
 	            if (properties == null) {
 	                properties = new ArrayList<>(); 
 	            }
 	            System.out.println("Properties deleted against user id :"+(int) request.getSession().getAttribute("propManagerId"));

 	            request.getSession().setAttribute("Currentproperties", properties);
 	            System.out.println("Session attribute set: " + request.getSession().getAttribute("Currentproperties"));
 	            
 	           request.setAttribute("deletesuccess", "Details Deleted Successfully !!");

                }else {
                	System.out.println("Deletion failed");
                }
                
                action="deleteProperty";

                break;
                
            case "viewByArea":
                String searchArea = request.getParameter("area");
                List<property> filteredProperties = dao.getPropertiesByArea(searchArea);

                request.getSession().setAttribute("Currentproperties", filteredProperties);
                request.setAttribute("searchArea", searchArea); 
                success = !filteredProperties.isEmpty();   
                return mapping.findForward("viewByAreaPage");
                
                
            case "occupiedProperties":
                System.out.println("Inside occupiedProperties");
                
                int managerid=(int) request.getSession().getAttribute("propManagerId");
                System.out.println("Loggedin user id :"+managerid);

                List<property> occupiedProperties = dao.getOccupiedProperties(managerid);

                request.getSession().setAttribute("Currentproperties", occupiedProperties);             
                success = !occupiedProperties.isEmpty();
                return mapping.findForward("viewOccupiedPage");   
                
 }
        
        request.setAttribute("currentAction", action);
        request.setAttribute("message", success ? "Action successful !!" : "Action failed !!");
        System.out.println("Redirecting to: " + (success ? "actionSuccess" : "actionFailure"));
        System.out.println("Response not committed: " + !response.isCommitted());
        return success ? mapping.findForward("actionSuccess") : mapping.findForward("actionfailure");
    }
    
    
}
