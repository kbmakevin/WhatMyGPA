package com.whatmygpa.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whatmygpa.dao.CoursesService;

/**
 * Servlet implementation class TranscriptServlet
 */
@WebServlet("/Transcript")
public class TranscriptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("transcript.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("showAddCourseForm") != null) {

			// show add course form
			request.setAttribute("allCourses", CoursesService.getAllCourses());
			request.setAttribute("operationHeader", "Add");
			request.getRequestDispatcher("marks_form.jsp").forward(request, response);

		} else if (request.getParameter("addCourse") != null) {

			addCourse(request, response);

		}
	}

	protected void addCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String courseCode = request.getParameter("code").toUpperCase();
		int gradeReceived = Integer.parseInt(request.getParameter("gradeReceived"));

		if (CoursesService.getCourse(courseCode) == null) {
			CoursesService.addCourse(courseCode, gradeReceived);
			request.setAttribute("resultMessage", "Course: " + courseCode + " has been added.");
		} else {
			request.setAttribute("resultMessage", "Course: " + courseCode + " already exists!");
		}
		// re-populate table with updated data
		doGet(request, response);
	}

}
