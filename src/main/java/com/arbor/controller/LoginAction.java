package com.arbor.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.arbor.Beans.LoginForm;
import com.arbor.Dao.PropertyManagerDAO;
import com.arbor.Dao.propertyDao;
import com.arbor.model.property;

public class LoginAction extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		LoginForm loginForm = (LoginForm) form;
        PropertyManagerDAO dao = new PropertyManagerDAO();
        propertyDao pdao= new propertyDao();
        property prop=new property();
        int propertyManagerId=-1;
        
        System.out.println("Inside LoginAction Controller with UserName: " + loginForm.getName() + " Password: " + loginForm.getPassword());

        // Validate user credentials
        boolean isValidUser = dao.validateUser(loginForm.getName(), loginForm.getPassword());

        if (isValidUser) {
            request.getSession().setAttribute("loggedInUser", loginForm.getName());
            System.out.println("User stored in session: " + request.getSession().getAttribute("loggedInUser"));

            
             propertyManagerId = pdao.getPropertyUserPass(loginForm.getName(), loginForm.getPassword());
            System.out.println("Retrieved propertyManagerID: " + propertyManagerId);
            
            
            
            if (propertyManagerId == -1) {
                System.out.println("No property manager found for this user.");
                request.setAttribute("error", "No properties associated with this account.");
                return mapping.findForward("failure");
            }
            
            request.getSession().setAttribute("propManagerId",propertyManagerId );

            //  properties  with the user
            List<property> properties = pdao.getPropertiesById(propertyManagerId);
            if (properties == null) {
                properties = new ArrayList<>(); 
            }
            System.out.println("Properties retrieved: " + properties.size());

            request.getSession().setAttribute("Currentproperties", properties);
            System.out.println("Session attribute set: " + request.getSession().getAttribute("Currentproperties"));

            return mapping.findForward("success");
        } else {
            request.setAttribute("error", "Invalid username or password");
            return mapping.findForward("failure");
        }
	}
}