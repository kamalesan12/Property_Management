package com.arbor.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.arbor.Beans.LoginForm;
import com.arbor.Dao.PropertyManagerDAO;

public class SignupAction extends Action {
	 @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {


	        LoginForm signupForm = (LoginForm) form;
	        PropertyManagerDAO dao = new PropertyManagerDAO();

	        System.out.println("Inside SignupAction with Name: " + signupForm.getName());

	        // user already exists check
	        boolean userExists = dao.checkIfUserExists(signupForm.getName());

	        if (userExists) {
	        	request.getSession().setAttribute("error", "Username already taken. Please choose another.");
	        	System.out.println("Username already taken. Please choose another.");
	            return mapping.findForward("failure"); // Redirect to signup failure page
	        }

	        // Insert new user into database
	        boolean isInserted = dao.insertNewUser(signupForm.getName(), signupForm.getPassword());

	        if (isInserted) {
	            request.setAttribute("message", "Signup successful! Please log in.");
	            return mapping.findForward("success"); // Redirect to login page
	        } else {
	        	request.getSession().setAttribute("error", "Username already taken. Please choose another.");
	        	System.out.println("Username already taken. Please choose another.");
	            return mapping.findForward("failure");
	        }
	    }
	}