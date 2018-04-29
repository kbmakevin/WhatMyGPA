package com.whatmygpa.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whatmygpa.dao.UsersService;
import com.whatmygpa.models.Users;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			response.getWriter().append("500: Server error;\n" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users user = UsersService.authenticateUsers(request.getParameter("username"), request.getParameter("password"));
		if (user != null) {
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("home.jsp").forward(request, response);

		} else {
			request.setAttribute("error", "Invalid login. Please try again.");
			doGet(request, response);
		}
	}
}
