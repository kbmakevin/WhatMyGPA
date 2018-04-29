package com.whatmygpa.servlets;

import java.io.IOException;

import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whatmygpa.dao.UsersService;

@WebServlet("/Register")
public class Register extends HttpServlet {
	/**
	 * 
	 */
	public Register() {
		super();
	}
	private static final long serialVersionUID = -3734815044129867359L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("register.jsp").forward(request, response);
		} catch (ServletException e) {
			
		} catch (IOException e) {
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("fName") + " " + request.getParameter("lName");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		if (!password.equals(confirmPassword)) {
			request.setAttribute("error", "Passwords do not match.");
			doGet(request, response);
			return;
		}
		
		try {
			UsersService.registerUsers(name,request.getParameter("username"), password);
			//request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("home.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
		} catch (PersistenceException e) {
			request.setAttribute("error", "Username already exists.");
			doGet(request, response);
			return;
		}
	}

}
