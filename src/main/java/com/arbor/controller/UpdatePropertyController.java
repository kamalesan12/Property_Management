package com.arbor.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.arbor.Beans.UpdateForm;
import com.arbor.Dao.propertyDao;
import com.arbor.model.property;

public class UpdatePropertyController extends Action {
	
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("Inside UpdateProperty Controller");
		
		UpdateForm updform=(UpdateForm) form;		
		property property=new property();
		propertyDao dao=new propertyDao();
		
		property.setId(updform.getId());
		property.setName(updform.getName());
		property.setPropertyManagerRef(updform.getManagerRef());
		property.setArea(updform.getArea());
		property.setCurrentValue(updform.getCurrentValue());
		property.setRentalPrice(updform.getRentalPrice());
		property.setOccupied("Y".equalsIgnoreCase(updform.getOccupied()));
		
		boolean isSuccess=dao.updatePropertyDetails(property);
		
		if(isSuccess) {
			
			 List<property> properties = dao.getPropertiesById((int)request.getSession().getAttribute("propManagerId"));
	            if (properties == null) {
	                properties = new ArrayList<>(); 
	            }
	            System.out.println("Properties updated with user id :"+(int) request.getSession().getAttribute("propManagerId"));

	            request.getSession().setAttribute("Currentproperties", properties);
	            System.out.println("Session attribute set: " + request.getSession().getAttribute("Currentproperties"));
			
			request.setAttribute("updatesuccess", "Details Updated Successfully !!");
			System.out.println("Details updated Successfully");

		}else {
			
	        request.setAttribute("currentAction", "Delete Property");
	        request.setAttribute("message", isSuccess ? "Action successful !!" : "Action failed !!");
	        System.out.println("Redirecting to: " + (isSuccess ? "actionSuccess" : "actionFailure"));


		}
		
        return isSuccess ? mapping.findForward("success") : mapping.findForward("failure");
			
	}

}
