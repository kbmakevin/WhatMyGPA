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
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			// request.getSession().removeAttribute("user");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			response.getWriter().append("500: Server error;\n" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users authenticatedUser = UsersService.authenticateUsers(request.getParameter("username"),
				request.getParameter("password"));
		if (authenticatedUser != null) {
			request.getSession().setAttribute("user", authenticatedUser);
			response.sendRedirect(request.getContextPath() + "/Home");

		} else {
			request.setAttribute("error", "Invalid login. Please try again.");
			doGet(request, response);
		}
	}
}
