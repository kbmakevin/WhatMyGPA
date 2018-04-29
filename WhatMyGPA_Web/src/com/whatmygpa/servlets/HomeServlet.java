package com.whatmygpa.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whatmygpa.dao.CoursesService;
import com.whatmygpa.dao.UsersService;
import com.whatmygpa.models.Users;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/Home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		if (request.getSession().getAttribute("user") != null) {
			Users authenticatedUser = (Users) request.getSession().getAttribute("user");

			if (authenticatedUser.getType().equalsIgnoreCase("admin")) {
				request.setAttribute("numberOfUsers", UsersService.getNumberUsers());
				request.setAttribute("numberOfCourses", CoursesService.getNumberCourses());
			}
		}

		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
